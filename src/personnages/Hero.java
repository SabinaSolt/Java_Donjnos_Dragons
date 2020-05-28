package personnages;

import armes.*;
import exceptions.PersonnageHorsPlateauException;
import ennemies.*;

import java.util.Scanner;

public abstract class Hero {
    //state of an object
    protected String name;
    protected String type;
    protected int niveauVie;
    protected int force;
    protected Arme arme;
    protected String protection;
    protected int forceMax;
    protected int vieMax;
    protected boolean dead;
    protected int caseCourante = 1;
    protected int derniereCase = 64;

    //constructor method
    public Hero() {
    }

    public Hero(String name) {
        this(name, 0, 0);
    }

    public Hero(String name,  int niveauVie, int force) {
        this.name = name;
        this.niveauVie = niveauVie;
        this.force = force;
        this.arme=new Motivation();
    }

//Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setNiveauVie(int niveauVie) {
        this.niveauVie = niveauVie;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public void setArme(Arme arme) {
        this.arme = arme;
    }

    public void setProtection(String protection) {
        this.protection = protection;
    }

    public void setCaseCourante(int nombrePas) throws PersonnageHorsPlateauException {
        this.caseCourante = this.caseCourante + nombrePas;
        if (this.caseCourante > derniereCase) {
            this.caseCourante = derniereCase;
            throw new PersonnageHorsPlateauException();
        }
    }

// Getters

    public String getName() {
        return name;
    }

    public int getNiveauVie() {
        return niveauVie;
    }

    public int getForce() {
        return force;
    }

    public Arme getArme() {
        return arme;
    }

    public String getProtection() {
        return protection;
    }
    public String getType() {
        return type;
    }

    public int getForceMax() {
        return forceMax;
    }

    public int getVieMax() {
        return vieMax;
    }

    public int getCaseCourante() {
        return caseCourante;
    }

    public int getDerniereCase() {
        return derniereCase;
    }

    public boolean isDead() {
        return dead;
    }

    public int getNumeroCaseCourante() {
        return caseCourante;
    }

    //behavior of an object
    @Override
    public String toString() {
        String str = "Name: " + this.name +"\nType: " + this.type+ "\nVie: " + this.niveauVie + "\nForce: " + this.force;
        return str;
    }

    public void augmenterAttaque(Arme arme) {
        this.arme = arme;
        this.force = this.force + arme.getForceAttack();
        if (this.force > this.forceMax) {
            this.force = this.forceMax;
            System.out.println("Tu es gonflé à bloc. Ta force est à son max " + this.force);
        } else {
            System.out.println("Wow, tu es devenu super balaise! Ta force passe à " + this.force);
        }

    }

    public void seguerir(int healing) {
        this.niveauVie = this.niveauVie + healing;
        if (this.niveauVie > this.vieMax) {
            this.niveauVie = this.vieMax;
            System.out.println("Tu es au top de ta forme! Ta vie est au zenith: " + this.niveauVie);
        } else {
            System.out.println("Ton niveau de vie passe à " + this.niveauVie);
        }

    }


    public void attaquer(Ennemi ennemi) {
        System.out.println("Tu attaques " + ennemi.getName() + " avec la force de " + this.force);
        ennemi.subirDommage(this.force);
    }

    public void fuir() {
        int caseRecule = 1 + (int) (Math.random() * 6);
        System.out.println("Tu fuis ton ennemi. Le destin te fais reculer de " + caseRecule+" cases");
        this.caseCourante = this.caseCourante - caseRecule;
        if (this.caseCourante < 1) {
            this.caseCourante = 1;
        }

        System.out.println("Tu retournes à la case " +this.caseCourante);
    }

    public void decisionHero(Ennemi ennemi) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Si tu veux te battre, tape Y. \nTape autre chose pour fuir");
        String response = sc.nextLine();
        if (response.equals("Y")) {
            attaquer(ennemi);
            ennemi.attaquer(this);
        } else {
            fuir();
        }
    }

    public void subirDommage(int force) {

        this.niveauVie -= force;
        if (this.niveauVie <= 0) {
            this.dead = true;
            this.niveauVie=0;
            System.out.println("Mince, tu es mort!");
        } else {
            System.out.println("Ton niveau de vie passe à " + this.niveauVie);
        }
    }

}


