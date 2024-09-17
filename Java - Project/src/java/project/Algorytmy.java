package java.project;

import java.math.*;
import java.util.*;

public class Algorytmy {
    
    //inicjalizacja wszystkich potrzebnych zmiennych
    private double doubleA, doubleB;
    private BigDecimal a,b;
    private String zmiennaA, zmiennaB, wynikS, WzP, wynikA, wynikB, pobierzA, pobierzB, zmiennaW;
    private int sumaPrzecinkow=0;
    private long maleA, maleB;
    private BigInteger duzeA, duzeB;
    private BigInteger wynik;
    private StringBuilder odw, wynikW;
    private boolean minus;
    
    //ustawienie wartości zero dla BigInteger'a
    private void duzeZero(){
        this.wynik = BigInteger.valueOf(0);
    }
    
    //mechanizm wyrażeń regularnych sprawdzający poprawność zapisu liczb
    private void sprawdzenie(){
        if(this.pobierzA.charAt(0)==',' || this.pobierzA.charAt(0)=='.'){
            System.out.println("Blad podczas wpisywania danych, sprawdz pisownie");
            System.exit(0);
        }
        if(this.pobierzB.charAt(0)==',' || this.pobierzB.charAt(0)=='.'){
            System.out.println("Blad podczas wpisywania danych, sprawdz pisownie");
            System.exit(0);
        }
        if(this.pobierzA.contains(".")){
            System.out.println("Blad podczas wpisywania danych, sprawdz pisownie");
            System.exit(0);
        }
        if(this.pobierzB.contains(".")){
            System.out.println("Blad podczas wpisywania danych, sprawdz pisownie");
            System.exit(0);
        }
        if(!this.pobierzA.matches("^-*[0-9]+,{1}+[0-9]+")){
            System.out.println("Blad podczas wpisywania danych, sprawdz pisownie");
            System.exit(0);
        }
        if(!this.pobierzB.matches("^-*[0-9]+,{1}+[0-9]+")){
            System.out.println("Blad podczas wpisywania danych, sprawdz pisownie");
            System.exit(0);
        }
    }
    
    //mechanizm zamienia wartości string na wartości typu double
    private void replaceNparse(){
        try{
            this.pobierzA=this.pobierzA.replace(",", ".");
            this.pobierzB=this.pobierzB.replace(",", ".");
            this.doubleA=Double.parseDouble(pobierzA);
            this.doubleB=Double.parseDouble(pobierzB);   
        }catch(NumberFormatException e){
            System.out.println("Blad podczas konwersji");
            System.out.println(e);
        }
    }
    
    //mechanizm pobierania danych, sprawdzenie czy można zadanie zrobić szybko czy też nie
    public void pobierzDane(){
        duzeZero();
        try{
            Scanner in = new Scanner(System.in,"CP1250");
            System.out.print("Podaj pierwsza liczbe zmiennoprzecinkowa (#,#): ");
            this.pobierzA=in.nextLine();
            System.out.print("Podaj druga liczbe zmiennoprzecinkowa (#,#): ");
            this.pobierzB=in.nextLine();
            sprawdzenie();
            replaceNparse();
            if(this.doubleA==1.0 || this.doubleB==1.0 || this.doubleA==-1.0 || this.doubleB==-1.0){
                szybkie();
            }
            else{
                zamianaDS();
            }
        }
        catch(Exception e){
            System.out.println("Blad podczas wpisywania danych, sprawdz pisownie");
            System.out.println(e);
        }
    }
    
    //mechanizm zmiany wartości typu double na BigDecimal dla ułatwienia 
    //operowania na liczbach, zmiana BD na string
    private void zamianaDS(){
        try{
            this.a = BigDecimal.valueOf(this.doubleA);
            this.b = BigDecimal.valueOf(this.doubleB);
            this.zmiennaA= this.a.toString();
            this.zmiennaB= this.b.toString();
            czyMinus();
        }catch(Exception e){
            System.out.println("Blad podczas konwersji double -> string");
            System.out.println(e);
        }
    }
    
    //mechanizm sprawdzajacy czy w danym łancuchu znaków 
    //znajduje się minus, jednym jak i drugim
    private void czyMinus(){
        try{
            boolean minusA=false, minusB=false;
            if(this.zmiennaA.contains("-")){
                this.zmiennaA = this.zmiennaA.replace("-", "");
                minusA=true;
            }
            if(this.zmiennaB.contains("-")){
                this.zmiennaB = this.zmiennaB.replace("-", "");
                minusB=true;
            }

            if((minusA==true && minusB==false) || (minusA==false && minusB==true)){
                this.minus=true;
            }
            else{
                this.minus=false;
            }
            przecinek();
        }catch(Exception e){
            System.out.println("Blad podczas usuwania znaku -");
            System.out.println(e);
        }
    }
    
    //mechanizm obliczający ile miejsc po przecinku znajduje się w obydwu liczbach
    private void przecinek(){
        try{
            int xA = this.zmiennaA.indexOf('.');
            int xB = this.zmiennaB.indexOf('.');
            for(int i = xA+1; i<this.zmiennaA.length(); i++){
                this.sumaPrzecinkow+=1;
            }
            for(int i = xB+1; i<this.zmiennaB.length(); i++){
                this.sumaPrzecinkow+=1;
            }
            zamianaSL();
        }catch(Exception e){
            System.out.println("Blad podczas zliczania miejsc po przecinku");
            System.out.println(e);
        }
    }
    
    //mechanizm konwersji stringa na Longa na BigIntegera dla ułatwienia obliczeń
    private void zamianaSL(){
        try{
            this.zmiennaA = this.zmiennaA.replace(".", "");
            this.zmiennaB = this.zmiennaB.replace(".", "");
            try{
                this.maleA = Long.parseLong(this.zmiennaA);
                this.maleB = Long.parseLong(this.zmiennaB);
                this.duzeA = BigInteger.valueOf(this.maleA);
                this.duzeB = BigInteger.valueOf(this.maleB);
                dodawanie();
            }catch(NumberFormatException e){
                System.out.println("Blad podczas zamiany string -> long");
                System.out.println(e);
            }
        }catch(Exception e){
            System.out.println("Blad podczas usuwania przecinka");
            System.out.println(e);
        }
    }
    
    //podstawowy mechanizm w którym liczba B dodaje sama siebie A razy
    private void dodawanie(){
        try{
            for(int i = 0; i<this.maleA; i++){
                this.wynik = this.wynik.add(this.duzeB);
            }
        }catch(Exception e){
            System.out.println("Blad podczas dodawania liczb");
            System.out.println(e);
        }
        odwracanieStringa();
    }
    
    //mechanizm odwracania stringa z wynikiem, aby prościej policzyć miejsce zerowe
    private void odwracanieStringa(){    
        try{
            int radix = 10;
            this.wynikS = this.wynik.toString(radix);
            StringBuilder str = new StringBuilder(this.wynikS);
            this.odw = str.reverse();
            this.WzP = "";
            odwrocenieX2();
        }catch(Exception e){
            System.out.println("Blad podczas odwracania stringa");
            System.out.println(e);
        }
    }
    
    //mechanizm łączenia stringów z przecinkiem, w zależności czy miejsc po przecinku
    //jest więcej czy mniej niż wynosi długość stringa
    private String laczenie(){
        try{
            if(this.odw.length()>=this.sumaPrzecinkow){
                for(int i = 0; i<this.odw.length(); i++){
                    if(i+1==this.sumaPrzecinkow){
                        this.WzP+=this.odw.charAt(i);
                        this.WzP+=",";
                    }
                    else{
                        this.WzP+=this.odw.charAt(i);
                    }
            }
            }
            else{
                for(int i = 0; i<this.sumaPrzecinkow+1; i++){
                    if(i+1>this.sumaPrzecinkow){
                        this.WzP+="0";
                    }
                    else if(i+1==this.sumaPrzecinkow){
                        this.WzP+="0";
                        this.WzP+=",";
                    }
                    else if(i+1<this.sumaPrzecinkow){
                        if(this.odw.length()>=i+1){
                            this.WzP+=this.odw.charAt(i);
                        }
                        else{
                            this.WzP+="0";
                        }
                    }
                }
            }
        }catch(Exception e){
            System.out.println("Blad podczas skladania liczb w calosc");
            System.out.println(e);
        }
        return this.WzP;
    }
    
    //mechanizm ponownego odwrócenia stringa, do pierwotnej właściwej formy
    //wraz ze znakiem ,
    private void odwrocenieX2(){
        try{
            StringBuilder zmiennaW = new StringBuilder(laczenie());
            this.wynikW = zmiennaW.reverse();
            this.zmiennaW=this.wynikW.toString();
            if(this.zmiennaW.startsWith(",")){
                this.zmiennaW="0"+this.zmiennaW;
            }
            wypiszWynik();
        }catch(Exception e){
            System.out.println("Blad podczas ponownego odwracania stringa");
            System.out.println(e);
        }
    }
    
    //funkcja wypisująca wynik, odpowiednio wcześniej korygując
    //pojawiające się zera na początku lub na końcu
    private void wypiszWynik(){
        try{
            if(this.minus){
                System.out.printf("Wynik mnozenia tych liczb wynosi: -%s \n", this.zmiennaW);
            }
            else{
                System.out.printf("Wynik mnozenia tych liczb wynosi: %s \n", this.zmiennaW);
            }
        }catch(Exception e){
            System.out.println("Blad podczas wypisywania wyniku");
            System.out.println(e);
        }
    }
    
    //funkcja która wypisuje "pomnożone" wartości w przypadku gdy jedna z wartości to 1 lub -1
    private void szybkie(){
        try{
            this.pobierzA=this.pobierzA.replace(".", ",");
            this.pobierzB=this.pobierzB.replace(".", ",");
            if(this.doubleA==-1.0&&this.doubleB>0){
                System.out.printf("Wynik mnozenia tych liczb wynosi: -%s \n", this.pobierzB);
            }
            else if(this.doubleA==1.0&&this.doubleB<0){
                System.out.printf("Wynik mnozenia tych liczb wynosi: -%s \n", this.pobierzB);
            }
            else if(this.doubleA==1.0&&this.doubleB>0){
                System.out.printf("Wynik mnozenia tych liczb wynosi: %s \n", this.pobierzB);
            }
            else if(this.doubleA==-1.0&&this.doubleB<0){
                System.out.printf("Wynik mnozenia tych liczb wynosi: %s \n", this.pobierzB);
            }
            if(this.doubleB==-1.0&&this.doubleA>0){
                System.out.printf("Wynik mnozenia tych liczb wynosi: -%s \n", this.pobierzA);
            }
            else if(this.doubleB==1.0&&this.doubleA<0){
                System.out.printf("Wynik mnozenia tych liczb wynosi: -%s \n", this.pobierzA);
            }
            else if(this.doubleB==1.0&&this.doubleA>0){
                System.out.printf("Wynik mnozenia tych liczb wynosi: %s \n", this.pobierzA);
            }
            else if(this.doubleB==-1.0&&this.doubleA<0){
                System.out.printf("Wynik mnozenia tych liczb wynosi: %s \n", this.pobierzA);
            }
        }catch(Exception e){
            System.out.println("Blad podczas wypisywania wyniku");
            System.out.println(e);
        }
    }
}

