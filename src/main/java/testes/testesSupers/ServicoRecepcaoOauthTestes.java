/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes.testesSupers;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.UtilSBApiRestClient;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.UtilSBApiRestClientOauth2;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioAnonimo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioSistemaRoot;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import java.util.Map;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import spark.Spark;

/**
 *
 * @author sfurbino
 */
public class ServicoRecepcaoOauthTestes {

    public static void iniciarServico() {
        MapaObjetosProjetoAtual.adcionarObjeto(UsuarioAnonimo.class);
        MapaObjetosProjetoAtual.adcionarObjeto(UsuarioSistemaRoot.class);
        Spark.port(7666);
        Spark.get("/solicitacaoAuth2Recept/*", (req, resp) -> {

            try {

                if (req.raw().getRequestURI().contains(UtilSBApiRestClientOauth2.PATH_TESTE_DE_VIDA_SERVICO_RECEPCAO)) {
                    return "OK";
                }
                Map<String, String[]> parametros = UtilSBApiRestClient.getParametroHttpServletRequestQuery(req.raw());
                String tipoAplicacao = parametros.get("tipoAplicacao")[0];
                if (!UtilSBApiRestClient.receberCodigoSolicitacaoOauth(req.raw(), tipoAplicacao)) {
                    throw new UnsupportedOperationException("falha recebendo codigo de solictação de token Oauth");
                }

            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando " + t.getMessage(), t);
                return "FALHOU";
            }

            return "ok";
        });

    }

}
