package fr.robguju.just_move;


//on crée une classe objet pour un article formé d'un titre, d'un résumé, d'un tag identificateur pour l'image
//d'un bouton favori et d'un texte
public class Article {

    private String title;
    private String resume;
    private String tag;
    public boolean fav;
    private String text;

    //constructeur
    public Article(String title, String tag, String resume, boolean fav, String text) {
        this.title = title;
        this.resume = resume;
        this.tag = tag;
        this.fav = fav;
        this.text = text;
    }

    //methodes accesseurs
    public String getTitle() {
        return title;
    }

    public String getTag() {
        return tag;
    }

    public String getResume() {
        return resume;
    }

    public boolean isFav() {return fav; }

    public String getText() {return text; }

}
