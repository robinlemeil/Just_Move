package fr.robguju.just_move;


//on crée une classe objet pour un article formé d'un titre, d'un résumé et d'un tag identificateur pour l'image
public class Article {

    private String title;
    private String resume;
    private String tag;
    public boolean fav = false;

    public Article(String title, String tag, String resume, boolean fav) {
        this.title = title;
        this.resume = resume;
        this.tag = tag;
        this.fav = fav;
    }

    public String getTitle() {
        return title;
    }

    public String getTag() {
        return tag;
    }

    public String getResume() {
        return resume;
    }

}
