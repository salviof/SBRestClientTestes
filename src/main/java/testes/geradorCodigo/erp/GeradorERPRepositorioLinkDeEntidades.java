/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes.geradorCodigo.erp;

import com.super_bits.modulosSB.SBCore.modulos.erp.ItfApiErpSuperBits;
import org.coletivojava.fw.utilCoreBase.UtilSBCoreReflexaoAPIERP;
import testesFW.geradorDeCodigo.GeradorClasseGenerico;

/**
 *
 * @author sfurbino
 */
public class GeradorERPRepositorioLinkDeEntidades
        extends GeradorClasseGenerico {

    public GeradorERPRepositorioLinkDeEntidades(ItfApiErpSuperBits pFabrica) {
        super(UtilSBCoreReflexaoAPIERP.getPacoteImplementacaoERP(pFabrica), UtilSBCoreReflexaoAPIERP.getNomeClasseAnotacaoImplementacao(pFabrica) + "RepositorioLinkEntidade");
        getCodigoJava().addImport(pFabrica.getInterface());
        getCodigoJava().implementInterface(pFabrica.getInterface());
        getCodigoJava().addAnnotation(pFabrica.getAnotacao());

    }

    @Override
    public void salvarEmDiretorioPadraoSubstituindoAnterior() {
        throw new UnsupportedOperationException("A implementação deve ser gerada sem substituicao da classe anterior");
    }
}
