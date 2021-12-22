/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes.spark;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenGestaoOauth;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.TipoClienteOauth;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.UtilSBApiRestClient;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import spark.Spark;

/**
 *
 * @author sfurbino
 * @since 17/12/2019
 * @version 1.0
 */
public class ServidorOauthRecepcaoSpark extends Thread {

    private final int porta;
    private final String caminho;
    private final ItfTokenGestaoOauth token;

    public ServidorOauthRecepcaoSpark(ItfTokenGestaoOauth pToken, int porta, String pCaminho) {
        this.porta = porta;
        caminho = pCaminho;
        token = pToken;
    }

    @Override
    public void run() {
        MapaObjetosProjetoAtual.adcionarObjeto(TipoClienteOauth.class);

        Spark.port(Integer.valueOf(porta));

        Spark.get(caminho, (req, res) -> {
            try {
                String tipoAplicacao = req.queryParamOrDefault("tipoAplicacao", null);
                if (UtilSBApiRestClient.receberCodigoSolicitacaoOauth(req.raw(), tipoAplicacao)) {
                    return "Obrigado, agora estamos conectados com sua conta.";
                } else {
                    return "Falha Gerando Código de acesso";
                }

            } catch (Throwable t) {
                return "Erro Maluco" + t.getMessage();
            }

        });

    }

}
