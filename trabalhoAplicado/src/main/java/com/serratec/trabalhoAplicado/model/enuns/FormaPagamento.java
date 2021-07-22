package com.serratec.trabalhoAplicado.model.enuns;

public enum FormaPagamento {


    CREDITO("credito"),
    DEBITO("debito"),
    DINHEIRO("dinheiro"),
    PIX("pix");

    private String formaPagamento;

    FormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }
}

