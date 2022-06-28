/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes.testesSupers;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.ItfFabricaIntegracaoRest;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import testes.geradorCodigo.GeradorApiIntegracaoRest;
import testes.geradorCodigo.GeradorGestaoTokenAcessoIntegracaoRest;
import testes.geradorCodigo.GeradorImplementacaoIntegracaoRest;
import testes.geradorCodigo.GeradorImplementacaoIntegracaoRestHeaderPadrao;

/**
 *
 * @author sfurbino
 * @since 16/12/2019
 * @version 1.0
 */
public class TestesApiRest {

    public void gerarCodigosChamadasEndpoint(Class<? extends ItfFabricaIntegracaoRest> pIntegrador) {

        for (ItfFabricaIntegracaoRest pFabrica : pIntegrador.getEnumConstants()) {
            try {
                gerarCodigo(pFabrica);
            } catch (Throwable y) {
                System.out.println(y.getMessage());
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro gerando código", y);
            }
        }
    }

    public void gerarCodigo(ItfFabricaIntegracaoRest pEndpoint) {
        GeradorApiIntegracaoRest gerador = new GeradorApiIntegracaoRest(pEndpoint);
        gerador.salvarEmDiretorioPadraoSubstituindoAnterior();
        GeradorImplementacaoIntegracaoRest geradorImp = new GeradorImplementacaoIntegracaoRest(pEndpoint);
        geradorImp.salvarEmDiretorioPadraCASO_NAO_EXISTA();
        GeradorGestaoTokenAcessoIntegracaoRest geradorToken = new GeradorGestaoTokenAcessoIntegracaoRest(pEndpoint);
        geradorToken.salvarEmDiretorioPadraCASO_NAO_EXISTA();
        GeradorImplementacaoIntegracaoRestHeaderPadrao geradorheader = new GeradorImplementacaoIntegracaoRestHeaderPadrao(pEndpoint);
        geradorheader.salvarEmDiretorioPadraCASO_NAO_EXISTA();
    }

    public void iniciarServicoRecepcaoOauth() {
        ServicoRecepcaoOauthTestes.iniciarServico();
    }

}
