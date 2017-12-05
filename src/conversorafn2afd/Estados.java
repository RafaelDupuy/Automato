/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversorafn2afd;

/**
 *
 * @author 144141065
 */
public class Estados {
    
    private String statusAtual;
    private String valor;
    private String statusAlvo;
    private String estadoFinal;
    

    public Estados(String statusAtual, String valor, String statusAlvo,String estadoFinal) {
        this.statusAtual = statusAtual;
        this.valor = valor;
        this.statusAlvo = statusAlvo;
        this.estadoFinal = estadoFinal;
        
    }
    
    
    
   

    public String getStatusAtual() {
        return statusAtual;
    }

    public void setStatusAtual(String statusAtual) {
        this.statusAtual = statusAtual;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getStatusAlvo() {
        return statusAlvo;
    }

    public void setStatusAlvo(String statusAlvo) {
        this.statusAlvo = statusAlvo;
    }

    public String getEstadoFinal() {
        return estadoFinal;
    }

    public void setEstadoFinal(String estadoFinal) {
        this.estadoFinal = estadoFinal;
    }

    
    
    
}
