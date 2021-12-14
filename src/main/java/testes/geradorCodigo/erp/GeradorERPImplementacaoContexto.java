/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes.geradorCodigo.erp;

import com.super_bits.modulosSB.SBCore.modulos.erp.ItfApiErpSuperBits;
import com.super_bits.modulosSB.SBCore.modulos.erp.ItfServicoLinkDeEntidadesERP;
import org.coletivojava.fw.utilCoreBase.UtilSBCoreReflexaoAPIERP;
import testesFW.geradorDeCodigo.GeradorClasseGenerico;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.erp.repositorioLinkEntidades.RepositorioLinkEntidadesGenerico;

/**
 *
 * @author desenvolvedor
 */
public class GeradorERPImplementacaoContexto extends GeradorClasseGenerico {

    public GeradorERPImplementacaoContexto(ItfApiErpSuperBits pFabrica) {
        super(UtilSBCoreReflexaoAPIERP.getPacoteImplementacaoERP(pFabrica), UtilSBCoreReflexaoAPIERP.getNomeClasseAnotacaoImplementacao(pFabrica) + "impl");
        getCodigoJava().addImport(ItfServicoLinkDeEntidadesERP.class);
        getCodigoJava().addImport(pFabrica.getClasseImplementacaoPadrao());
        getCodigoJava().implementInterface(pFabrica.getClasseImplementacaoPadrao());
        getCodigoJava().extendSuperType(RepositorioLinkEntidadesGenerico.class);
        getCodigoJava().addAnnotation(pFabrica.getAnotacao());

    }

    @Override
    public void salvarEmDiretorioPadraoSubstituindoAnterior() {
        throw new UnsupportedOperationException("A implementação deve ser gerada sem substituicao da classe anterior");
    }

}
