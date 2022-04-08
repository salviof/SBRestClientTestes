/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes.geradorCodigo.erp.dto;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.erp.dto.DTO_SBGENERICO;
import com.super_bits.modulosSB.SBCore.modulos.erp.ItfApiErpSuperBits;
import org.coletivojava.fw.utilCoreBase.UtilSBCoreReflexaoAPIERP;
import testesFW.geradorDeCodigo.GeradorClasseGenerico;

/**
 *
 * @author sfurbino
 */
public class GeradorDTOPojo extends GeradorClasseGenerico {

    public GeradorDTOPojo(ItfApiErpSuperBits pFabrica, Class pInterfacePojo) {
        super(UtilSBCoreReflexaoAPIERP.getPacoteApiDTO(pFabrica, pInterfacePojo), UtilSBCoreReflexaoAPIERP.getNomeDTOClassePojo(pInterfacePojo));
        String classeInterface = UtilSBCoreReflexaoAPIERP.getPacoteApiDTO(pFabrica, pInterfacePojo) + "." + UtilSBCoreReflexaoAPIERP.getNomeDTOInterface(pInterfacePojo);
        String classeProcessador = UtilSBCoreReflexaoAPIERP.getPacoteApiDTO(pFabrica, pInterfacePojo) + "." + UtilSBCoreReflexaoAPIERP.getNomeDTOProcessClassePojo(pInterfacePojo);
        getCodigoJava().addImport(classeInterface);
        getCodigoJava().addImport(classeProcessador);

        getCodigoJava().addInterface(UtilSBCoreReflexaoAPIERP.getNomeDTOInterface(pInterfacePojo));
        getCodigoJava().addImport(DTO_SBGENERICO.class);
        getCodigoJava().setSuperType(DTO_SBGENERICO.class.getSimpleName() + "<" + UtilSBCoreReflexaoAPIERP.getNomeDTOInterface(pInterfacePojo) + ">");

        getCodigoJava().addMethod("public " + UtilSBCoreReflexaoAPIERP.getNomeDTOInterface(pInterfacePojo) + "(String pJson) { \n"
                + " super(" + UtilSBCoreReflexaoAPIERP.getNomeDTOProcessClassePojo(pInterfacePojo) + ".class, pJson); \n }"
        ).setConstructor(true);
        getCodigoJava().addMethod("public " + UtilSBCoreReflexaoAPIERP.getNomeDTOInterface(pInterfacePojo) + "() { \n"
                + " super(null,null); \n }"
        ).setConstructor(true);

    }

}
