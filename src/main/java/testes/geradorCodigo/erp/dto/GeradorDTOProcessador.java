/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes.geradorCodigo.erp.dto;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.erp.dto.DTO_SBGENERICO;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.erp.dto.DTO_SB_JSON_PROCESSADOR_GENERICO;
import com.super_bits.modulosSB.SBCore.modulos.erp.ItfApiErpSuperBits;
import org.coletivojava.fw.utilCoreBase.UtilCRCReflexaoAPIERP;
import testesFW.geradorDeCodigo.GeradorClasseGenerico;

/**
 *
 * @author sfurbino
 */
public class GeradorDTOProcessador extends GeradorClasseGenerico {

    public GeradorDTOProcessador(ItfApiErpSuperBits pFabrica, Class pInterfacePojo) {
        super(UtilCRCReflexaoAPIERP.getPacoteApiDTO(pFabrica, pInterfacePojo), UtilCRCReflexaoAPIERP.getNomeDTOProcessClassePojo(pInterfacePojo));
        String dtoBind = UtilCRCReflexaoAPIERP.getPacoteApiDTO(pFabrica, pInterfacePojo) + "." + UtilCRCReflexaoAPIERP.getNomeDTOClassePojo(pInterfacePojo);
        getCodigoJava().addImport(DTO_SB_JSON_PROCESSADOR_GENERICO.class);
        getCodigoJava().addImport(dtoBind);
        getCodigoJava().setSuperType(DTO_SB_JSON_PROCESSADOR_GENERICO.class.getSimpleName() + "<" + UtilCRCReflexaoAPIERP.getNomeDTOClassePojo(pInterfacePojo) + ">");
        String metodoConsturcotor = "public " + UtilCRCReflexaoAPIERP.getNomeDTOProcessClassePojo(pInterfacePojo) + "(){\n "
                + "super(" + UtilCRCReflexaoAPIERP.getNomeDTOClassePojo(pInterfacePojo) + ".class); \n }";
        System.out.println(metodoConsturcotor);
        getCodigoJava().addMethod(metodoConsturcotor).setConstructor(true);

        //   public JsonProcessParcelaAssinaturaDTO() {
        //    super(DTOCtPagarReceberJsonParcelaAssinatura.class);
        //}
        //DTO_SB_JSON_PROCESSADOR_GENERICO<DTOCtPagarReceberJsonParcelaAssinatura>
    }
}
