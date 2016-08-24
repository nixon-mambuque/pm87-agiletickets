package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		
		BigDecimal preco = null;

		boolean sessaoCinema = sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.CINEMA);
		boolean sessaoShow = sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.SHOW);
		boolean sessaoBallet = sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.BALLET);
		boolean sessaoOrquestra = sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.ORQUESTRA);
		

		if (sessaoCinema || sessaoShow) {
			// quando estiver acabando os ingressos...
			if ((sessao.getTotalIngressos() - sessao.getIngressosReservados())/ sessao.getTotalIngressos().doubleValue() <= 0.05) {
				
				preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
				
			} else {
				
				preco = sessao.getPreco();
			}
			
			//valorDecimal(sessao, 0.05, 0.10);
		} 
		
		
		else if (sessaoBallet) {
			
			if ((sessao.getTotalIngressos() - sessao.getIngressosReservados())/ sessao.getTotalIngressos().doubleValue() <= 0.50) {
				
				preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.20)));
				
			} else {
				
				preco = sessao.getPreco();
			}
			
			//valorDecimal(sessao, 0.50, 0.20);

			if (sessao.getDuracaoEmMinutos() > 60) {
				
				preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			}
		} 
		
		
		
		else if (sessaoOrquestra) {
			
			if ((sessao.getTotalIngressos() - sessao.getIngressosReservados())/ sessao.getTotalIngressos().doubleValue() <= 0.50) {
				
				preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.20)));
				
			} else {
				
				preco = sessao.getPreco();
			}
			//valorDecimal(sessao, 0.50, 0.20);

			if (sessao.getDuracaoEmMinutos() > 60) {
				
				preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			}
		} 
		
		
		else {
			// nao aplica aumento para teatro (quem vai é pobretão)
			preco = sessao.getPreco();
		}

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}
	
	private static BigDecimal valorDecimal(Sessao sessao, double valorP, double valorQ){
		if ((sessao.getTotalIngressos() - sessao.getIngressosReservados())/ sessao.getTotalIngressos().doubleValue() <= valorP) {
			
			return sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(valorQ)));
			
		} else {
			
			return sessao.getPreco();
		}
	}
	
	
}