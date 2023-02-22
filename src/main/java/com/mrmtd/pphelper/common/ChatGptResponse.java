package com.mrmtd.pphelper.common;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class ChatGptResponse {

	private String id;
	private String object;
	private long created;
	private String model;
	private List<Choice> choices;
	private Usage usage;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public long getCreated() {
		return created;
	}

	public void setCreated(long created) {
		this.created = created;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public List<Choice> getChoices() {
		return choices;
	}

	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

	public Usage getUsage() {
		return usage;
	}

	public void setUsage(Usage usage) {
		this.usage = usage;
	}

	public static class Choice {
		private String text;
		private int index;
		private Map<String, Object> logprobs;
		private String finishReason;

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public Map<String, Object> getLogprobs() {
			return logprobs;
		}

		public void setLogprobs(Map<String, Object> logprobs) {
			this.logprobs = logprobs;
		}

		public String getFinishReason() {
			return finishReason;
		}

		public void setFinishReason(String finishReason) {
			this.finishReason = finishReason;
		}
	}

	public static class Usage {
		private int promptTokens;
		private int completionTokens;
		private int totalTokens;

		public int getPromptTokens() {
			return promptTokens;
		}

		public void setPromptTokens(int promptTokens) {
			this.promptTokens = promptTokens;
		}

		public int getCompletionTokens() {
			return completionTokens;
		}

		public void setCompletionTokens(int completionTokens) {
			this.completionTokens = completionTokens;
		}

		public int getTotalTokens() {
			return totalTokens;
		}

		public void setTotalTokens(int totalTokens) {
			this.totalTokens = totalTokens;
		}
	}
	
	@Override
	public String toString() {
		
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
