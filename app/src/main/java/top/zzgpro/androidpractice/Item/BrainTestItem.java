package top.zzgpro.androidpractice.Item;

public class BrainTestItem {
    private String question;

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

    private String answer;
    public BrainTestItem(String question,String answer){
        this.question=question;
        this.answer=answer;
    }
}
