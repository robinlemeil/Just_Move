package fr.robguju.just_move;

public class Utilisateur {

    public String prenom, nom, mail, password;
    public String age, taille, poids, temps_bureau;

    public Utilisateur(){
    }

    public Utilisateur(String prenom,String nom,String age,String taille,String poids,String temps_bureau,String mail, String password){
        this.prenom = prenom;
        this.nom = nom;
        this.age = age;
        this.taille = taille;
        this.poids = poids;
        this.temps_bureau = temps_bureau;
        this.mail = mail;
        this.password = password;
    }
}
