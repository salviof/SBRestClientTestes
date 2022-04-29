/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testes.geradorCodigo.erp.conversorErpResfull;

import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.model.conversao.ConversorERPResfullToJsonAbs;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.model.conversao.ItfConversorERRestfullToJson;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.UtilSBRestful;
import com.super_bits.modulosSB.SBCore.modulos.erp.ItfSistemaERP;
import testesFW.geradorDeCodigo.GeradorClasseGenerico;

/**
 *
 * @author salvio
 */
public class GeradorConversorErpRestFullToJson extends GeradorClasseGenerico {

    public GeradorConversorErpRestFullToJson(ItfSistemaERP pSistem, Class pClasse) {
        super(UtilSBRestful.getPacoteClassesConversao(pSistem, pClasse), UtilSBRestful.getNomeClasseToJson(pSistem, pClasse));
        getCodigoJava().addImport(ItfConversorERRestfullToJson.class);
        getCodigoJava().addImport(ConversorERPResfullToJsonAbs.class);
        getCodigoJava().addInterface(ItfConversorERRestfullToJson.class);
        getCodigoJava().extendSuperType(ConversorERPResfullToJsonAbs.class);
    }

}
