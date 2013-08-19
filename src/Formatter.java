public class Formatter {

	private String[] hashtag;
	private String question;
	private String answer;

	private String questionf;
	private String answerf;
	private String[] hashtagf;

	private String post;

	public final static String PREFIX = "OQ";

	public Formatter() {
		hashtag = hashtagf = new String[1];
		hashtag[0] = hashtagf[0] = "";
		question = answer = questionf = answerf = post = "";
	}

	public String[] getHashtag() {
		return hashtag;
	}

	public void setHashtag(String[] hashtag) {
		this.hashtag = hashtag;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public void format() {
		questionf = question;
		
		if (question.charAt(0) == '"') {
			questionf = question.substring(1);
		}

		if (question.charAt(question.length() - 1) == '"') {
			questionf = question.substring(1, question.length() - 2);
		}

		questionf = questionf.replaceAll("\"\"", "\"");

		questionf = "Q: " + questionf;
		
		answerf = answer;

		if (answer.charAt(0) == '"') {
			answerf = answer.substring(1);
		}

		if (answer.charAt(answer.length() - 1) == '"') {
			answerf = answer.substring(1, answer.length() - 2);
		}

		answerf = answerf.replaceAll("\"\"", "\"");

		answerf = "A: " + answerf;

		hashtagf = new String[hashtag.length];

		for (int i = 0; i < hashtag.length; i++) {
			String temp = hashtag[i];
			temp = toTitleCase(temp);
			temp = "#" + PREFIX + temp;
			hashtagf[i] = temp;
		}

		post = questionf + "\n\n" + answerf + "\n\n";

		for (String hash : hashtagf) {
			post = post + hash + " ";
		}
	}

	public String getPost() {
		return post;
	}

	private static String toTitleCase(String input) {
		StringBuilder titleCase = new StringBuilder();
		boolean nextTitleCase = true;

		for (char c : input.toCharArray()) {
			if (Character.isSpaceChar(c)) {
				nextTitleCase = true;
			} else if (nextTitleCase) {
				c = Character.toTitleCase(c);
				nextTitleCase = false;
			}

			titleCase.append(c);
		}

		return titleCase.toString();
	}
}
