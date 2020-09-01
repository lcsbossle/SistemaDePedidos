package org.ufpr.sistemapedidos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Cpf
{
    private String cpfString = "";
    private List<Integer> digitos = new ArrayList<Integer>();
    private List<Integer> multiplicadores1 = new ArrayList<Integer>();
    private List<Integer> multiplicadores2 = new ArrayList<Integer>();
    private boolean valid;
    
    public Cpf(String cpfString)
    {
        this.cpfString = cpfString.replace(".", "").replace("-", "");
        this.digitos = digitosFromString(this.cpfString);
        
        int i;
        for(i=0 ; i<9; i++)
        {
            this.multiplicadores1.add(10-i);
        }
        
        int j;
        for(j=0; j<10; j++)
        {
            this.multiplicadores2.add(11-j);
        }
        this.setValid();
    }
    
    
    public Cpf()
    {
        Integer k;
        for(k=0 ; k < 9 ; k++)
        {
            int randomNum = ThreadLocalRandom.current().nextInt(0, 9 + 1);
            this.digitos.add(randomNum);
        }
        
        int i;
        for(i=0 ; i<9; i++)
        {
            this.multiplicadores1.add(10-i);
        }
        
        int j;
        for(j=0; j<10; j++)
        {
            this.multiplicadores2.add(11-j);
        }
        
        this.digitos.add(calcularDigito1());
        this.digitos.add(calcularDigito2());
        
        for(Integer d : digitos)
        {
            this.cpfString += Integer.toString(d);
        }
        this.setValid();
    }
    
    private List<Integer> digitosFromString(String str){
        if(str.length() != 11)
        {
            throw new RuntimeException("Formato de CPF inválido.");
        }
        List<Integer> digitos = new ArrayList<Integer>();
        String[] array = this.cpfString.split("");
        for(String s : array){
            try{
                digitos.add(Integer.parseInt(s));
            }
            catch(Exception ex){
                System.out.println("Dígito "+s+" inválido");
            }
        }
        return digitos;
    }
    
    private Integer calcularDigito1()
    {
        Integer sum = 0;
        Integer resto = 0;
        Integer dig1 = 0;
        Integer i;
        
        for(i=0 ; i<9 ; i++)
        {
            sum += this.digitos.get(i) * this.multiplicadores1.get(i);
        }
        resto = sum % 11;
        
        if(resto < 2){
            dig1 = 0;
        }
        else {
            dig1 = 11 - resto;
        }
        return dig1;
    }
    
    private Integer calcularDigito2()
    {
        Integer sum = 0;
        Integer resto = 0;
        Integer dig2 = 0;
        Integer j;
        
        for(j=0; j<10 ; j++)
        {
            sum += this.digitos.get(j) * this.multiplicadores2.get(j);
        }
        
                resto = sum % 11;
        
        if(resto < 2){
            dig2 = 0;
        }
        else {
            dig2 = 11 - resto;
        }

        return dig2;
    }

    private void setValid()
    {
        this.valid = true;
        if(
                this.digitos.get(0).equals(this.digitos.get(1)) &&
                this.digitos.get(1).equals(this.digitos.get(2)) &&
                this.digitos.get(2).equals(this.digitos.get(3)) &&
                this.digitos.get(3).equals(this.digitos.get(4)) &&
                this.digitos.get(4).equals(this.digitos.get(5)) &&
                this.digitos.get(5).equals(this.digitos.get(6)) &&
                this.digitos.get(6).equals(this.digitos.get(7)) &&
                this.digitos.get(7).equals(this.digitos.get(8)) &&
                this.digitos.get(8).equals(this.digitos.get(9))
                )
        {
            this.valid = false;
        }
        if(
                !Objects.equals(this.calcularDigito1(), this.digitos.get(9)) ||
                !Objects.equals(this.calcularDigito2(), this.digitos.get(10))
                ){
            this.valid = false;
        }
    }
    
    public boolean isValid()
    {
        return this.valid;
    }

    private List<Integer> getDigitos() {
        return digitos;
    }

    public String getCpfString() {
        return cpfString;
    }
    
    @Override
    public String toString(){
        String str = "";
        str += Integer.toString(digitos.get(0));
        str += Integer.toString(digitos.get(1));
        str += Integer.toString(digitos.get(2));
        str += ".";
        str += Integer.toString(digitos.get(3));
        str += Integer.toString(digitos.get(4));
        str += Integer.toString(digitos.get(5));
        str += ".";
        str += Integer.toString(digitos.get(6));
        str += Integer.toString(digitos.get(7));
        str += Integer.toString(digitos.get(8));
        str += "-";
        str += Integer.toString(digitos.get(9));
        str += Integer.toString(digitos.get(10));
        return str;
    }
}
