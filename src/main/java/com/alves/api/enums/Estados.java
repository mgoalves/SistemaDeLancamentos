package com.alves.api.enums;

public enum Estados {
	
		AC(""),
		AL(""),
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
	 	MG(""),
		PA(""),
		PB(""),
		PR(""),
		PE(""),
		PI(""),
		RJ(""),
		RN(""),
		RS(""),
		RO(""),
		RR(""),
	 	SC(""),
	 	SP(""),
		SE(""),
		TO("");
		
	    private String descricao;
    
	    //Construtor.
	    Estados(String descricao) {
	     
	        this.descricao = descricao;
	    }    

	    public String getDescricao() {
	        return descricao;
	    }
	    
}
