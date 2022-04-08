/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes.geradorCodigo.erp.dto;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.erp.dto.DTO_SBGENERICO;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.erp.dto.DTO_SB_JSON_PROCESSADOR_GENERICO;
import com.super_bits.modulosSB.SBCore.modulos.erp.ItfApiErpSuperBits;
import org.coletivojava.fw.utilCoreBase.UtilSBCoreReflexaoAPIERP;
import testesFW.geradorDeCodigo.GeradorClasseGenerico;

/**
 *
 * @author sfurbino
 */
public class GeradorDTOProcessador extends GeradorClasseGenerico {

    public GeradorDTOProcessador(ItfApiErpSuperBits pFabrica, Class pInterfacePojo) {
        super(UtilSBCoreReflexaoAPIERP.getPacoteApiDTO(pFabrica, pInterfacePojo), UtilSBCoreReflexaoAPIERP.getNomeDTOProcessClassePojo(pInterfacePojo));
        String dtoBind = UtilSBCoreReflexaoAPIERP.getPacoteApiDTO(pFabrica, pInterfacePojo) + "." + UtilSBCoreReflexaoAPIERP.getNomeDTOClassePojo(pInterfacePojo);
        getCodigoJava().addImport(DTO_SB_JSON_PROCESSADOR_GENERICO.class);
        getCodigoJava().addImport(dtoBind);
        getCodigoJava().setSuperType(DTO_SB_JSON_PROCESSADOR_GENERICO.class.getSimpleName() + "<" + UtilSBCoreReflexaoAPIERP.getNomeDTOClassePojo(pInterfacePojo) + ">");
        String metodoConsturcotor = "public " + UtilSBCoreReflexaoAPIERP.getNomeDTOProcessClassePojo(pInterfacePojo) + "(){\n "
                + "super(" + UtilSBCoreReflexaoAPIERP.getNomeDTOClassePojo(pInterfacePojo) + ".class); \n }";
        System.out.println(metodoConsturcotor);
        getCodigoJava().addMethod(metodoConsturcotor).setConstructor(true);

        //   public JsonProcessParcelaAssinaturaDTO() {
        //    super(DTOCtPagarReceberJsonParcelaAssinatura.class);
        //}
        //DTO_SB_JSON_PROCESSADOR_GENERICO<DTOCtPagarReceberJsonParcelaAssinatura>
    }
}
