/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes.spark;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenGestaoOauth;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.TipoClienteOauth;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.UtilSBApiRestClient;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.UtilSBApiRestClientOauth2;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.util.UtilSBERPRestFullClient;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import javax.servlet.http.HttpServletRequest;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
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
        MapaObjetosProjetoAtual.adcionarObjeto(TipoClienteOauth.class);
    }

    @Override
    public void run() {
        try {
            Spark.port(porta);

            Spark.get(caminho + "/*", (req, res) -> {
                try {
                    HttpServletRequest requisicao = req.raw();
                    if (!SBCore.isEmModoDesenvolvimento()) {
                        throw new UnsupportedOperationException("Este serviço foi homologado apenas para execução em modo teste");
                    } else {
                        requisicao.setAttribute("usuario", SBCore.getServicoSessao().getSessaoAtual().getUsuario());
                    }
                    String tipoAplicacao = req.queryParamOrDefault("tipoAplicacao", null);

                    if (UtilSBApiRestClient.receberCodigoSolicitacaoOauth(requisicao, tipoAplicacao)) {
                        return "Obrigado, agora estamos conectados com sua conta.";
                    } else {
                        return "Falha Gerando Código de acesso";
                    }

                } catch (Throwable t) {
                    res.status(401);
                    return "Erro Maluco" + t.getMessage();
                }

            });

            Spark.get(UtilSBApiRestClientOauth2.PATH_TESTE_DE_VIDA_SERVICO_RECEPCAO, (req, res) -> {
                try {
                    return UtilSBERPRestFullClient.TEXTO_RESPOSTA_WEBSERVICE_RESTFUL_PING_SUCESSO;

                } catch (Throwable t) {
                    res.status(401);
                    return "Erro Maluco" + t.getMessage();
                }

            });

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha iniciando servidor na prta" + porta + " path:" + caminho + t.getMessage(), t);
        }

    }

}
