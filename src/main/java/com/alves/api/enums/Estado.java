package com.alves.api.enums;

public enum Estado {
	
		AC("Acre"),
		AL("Alagoas"),
		AP(""),
		AM(""),
		BA(""),
		CE(""),
	 	DF(""),
	 	ES(""),
		GO(""),
		MA(""),
	 	MT(""),
	   	MS(""),
	 	MG("Minas Gerais"),
		PA(""),
		PB(""),
		PR(""),
		PE(""),
		PI(""),
		RJ("Rio de Janeiro"),
		RN(""),
		RS(""),
		RO(""),
		RR(""),
	 	SC("Santa Catarina"),
	 	SP(""),
		SE(""),
		TO("Tocantins");
		
	    private String descricao;
    
	    //Construtor.
	    Estado(String descricao) {
	     
	        this.descricao = descricao;
	    }    

	    public String getDescricao() {
	        return descricao;
	    }
	    
}
