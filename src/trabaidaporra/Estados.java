/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabaidaporra;

/**
 *
 * @author 144141065
 */
public class Estados {
    
    private String statusAtual;
    private String valor;
    private String statusAlvo;
    

    public Estados(String statusAtual, String valor, String statusAlvo) {
        this.statusAtual = statusAtual;
        this.valor = valor;
        this.statusAlvo = statusAlvo;
        
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

    
    
    
}
