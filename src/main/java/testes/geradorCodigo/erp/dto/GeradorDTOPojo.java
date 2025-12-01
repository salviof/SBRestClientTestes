/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes.geradorCodigo.erp.dto;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.erp.dto.DTO_SBGENERICO;
import com.super_bits.modulosSB.SBCore.modulos.erp.ItfApiErpSuperBits;
import org.coletivojava.fw.utilCoreBase.UtilCRCReflexaoAPIERP;
import testesFW.geradorDeCodigo.GeradorClasseGenerico;

/**
 *
 * @author sfurbino
 */
public class GeradorDTOPojo extends GeradorClasseGenerico {

    public GeradorDTOPojo(ItfApiErpSuperBits pFabrica, Class pInterfacePojo) {
        super(UtilCRCReflexaoAPIERP.getPacoteApiDTO(pFabrica, pInterfacePojo), UtilCRCReflexaoAPIERP.getNomeDTOClassePojo(pInterfacePojo));
        String classeInterface = UtilCRCReflexaoAPIERP.getPacoteApiDTO(pFabrica, pInterfacePojo) + "." + UtilCRCReflexaoAPIERP.getNomeDTOInterface(pInterfacePojo);
        String classeProcessador = UtilCRCReflexaoAPIERP.getPacoteApiDTO(pFabrica, pInterfacePojo) + "." + UtilCRCReflexaoAPIERP.getNomeDTOProcessClassePojo(pInterfacePojo);
        getCodigoJava().addImport(classeInterface);
        getCodigoJava().addImport(classeProcessador);

        getCodigoJava().addInterface(UtilCRCReflexaoAPIERP.getNomeDTOInterface(pInterfacePojo));
        getCodigoJava().addImport(DTO_SBGENERICO.class);
        getCodigoJava().setSuperType(DTO_SBGENERICO.class.getSimpleName() + "<" + UtilCRCReflexaoAPIERP.getNomeDTOInterface(pInterfacePojo) + ">");

        getCodigoJava().addMethod("public " + UtilCRCReflexaoAPIERP.getNomeDTOInterface(pInterfacePojo) + "(String pJson) { \n"
                + " super(" + UtilCRCReflexaoAPIERP.getNomeDTOProcessClassePojo(pInterfacePojo) + ".class, pJson); \n }"
        ).setConstructor(true);
        getCodigoJava().addMethod("public " + UtilCRCReflexaoAPIERP.getNomeDTOInterface(pInterfacePojo) + "() { \n"
                + " super(null,null); \n }"
        ).setConstructor(true);

    }

}
