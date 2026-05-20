package com.example.virasatnammaguidetourismtravelling

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.ResponseStoppedException
import com.google.ai.client.generativeai.type.content
import com.google.ai.client.generativeai.type.generationConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    private val _uiState: MutableStateFlow<UiState> =
        MutableStateFlow(UiState.Initial)
    val uiState: StateFlow<UiState> =
        _uiState.asStateFlow()

    // Using gemini-2.5-flash which is the active stable model with free tier
    private val generativeModel = GenerativeModel(
        modelName = "gemini-2.5-flash",
        apiKey = BuildConfig.API_KEY,
        generationConfig = generationConfig {
            temperature = 0.8f
            maxOutputTokens = 8192
        }
    )

    fun resetState() {
        _uiState.value = UiState.Initial
    }

    fun sendPrompt(prompt: String) {
        if (prompt.isBlank()) {
            resetState()
            return
        }

        _uiState.value = UiState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val isItinerary = prompt.contains("itinerary", ignoreCase = true) || 
                                 prompt.contains("plan", ignoreCase = true)
                
                val systemInstruction = if (isItinerary) {
                    "You are 'Virasat Namma Guide', a professional Karnataka tourism expert. Create a detailed, day-wise travel itinerary. Include morning, afternoon, and evening activities."
                } else {
                    "You are 'Virasat Namma Guide', a knowledgeable tour guide for Karnataka. Answer this travel query briefly and helpfully."
                }

                val response = generativeModel.generateContent(
                    content {
                        text("$systemInstruction User: $prompt")
                    }
                )

                response.text?.let { outputContent ->
                    _uiState.value = UiState.Success(outputContent)
                } ?: run {
                    _uiState.value = UiState.Error("AI returned an empty response. Please try again.")
                }
            } catch (e: ResponseStoppedException) {
                e.response.text?.let { partialContent ->
                    _uiState.value = UiState.Success(partialContent)
                } ?: run {
                    _uiState.value = UiState.Error("AI Support Error: Content generation stopped (${e.response.candidates.firstOrNull()?.finishReason}).")
                }
            } catch (e: Exception) {
                val errorMsg = e.localizedMessage ?: "Unknown AI Error"
                _uiState.value = UiState.Error("AI Support Error: $errorMsg")
            }
        }
    }
}
