package com.example.sameh.mymg;

public class Client {
    private String login;
    private String password;
    private String nom;
    private String prenom;
    private String email;
    private int carte;
    private int tel;

    public Client(){

    }
    public Client(String login,String password,String nom,String prenom,int carte,int tel, String email){
        this.login=login;
        this.password=password;
        this.nom=nom;
        this.prenom=prenom;
        this.email=email;
        this.carte=carte;
        this.tel=tel;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    public int getCarte() {
        return carte;
    }

    public void setCarte(int carte) {
        this.carte = carte;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }
}

