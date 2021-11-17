/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes.geradorCodigo.erp.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.erp.dto.ItfDTOSBJSON;
import com.super_bits.modulosSB.SBCore.modulos.erp.ItfApiErpSuperBits;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanComImagemGrande;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanComImagemMedio;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanComImagemPequena;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanComUploadImagemGrande;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanComUploadImagemPequena;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanComUploadImagensPadrao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanGenerico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanGenericoSomenteLeitura;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanReflexoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanVisualizavel;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.coletivojava.fw.utilCoreBase.UtilSBCoreReflexaoAPIERP;
import testesFW.geradorDeCodigo.GeradorInterfaceGenerico;

/**
 *
 * @author sfurbino
 */
public class GeradorDTOInterface extends GeradorInterfaceGenerico {

    /**
     *
     * @param pFabricaintegracao
     * @param interfacePojo
     */
    public GeradorDTOInterface(ItfApiErpSuperBits pFabrica, Class interfacePojo) {
        super(UtilSBCoreReflexaoAPIERP.getPacoteApiDTO(pFabrica, interfacePojo), UtilSBCoreReflexaoAPIERP.getNomeDTOInterface(interfacePojo));
        getCodigoJava().addImport(ItfDTOSBJSON.class);
        getCodigoJava().addImport(interfacePojo);
        //@JsonDeserialize(using = JsonProcessAssinaturaDTO.class)
        getCodigoJava().addImport(JsonDeserialize.class);
        getCodigoJava().addImport(UtilSBCoreReflexaoAPIERP.getPacoteApiDTO(pFabrica, interfacePojo) + "." + UtilSBCoreReflexaoAPIERP.getNomeDTOProcessClassePojo(interfacePojo));
        getCodigoJava().addAnnotation(JsonDeserialize.class).setLiteralValue("using", UtilSBCoreReflexaoAPIERP.getNomeDTOProcessClassePojo(interfacePojo) + ".class");
        getCodigoJava().addInterface(ItfDTOSBJSON.class.getSimpleName());
        getCodigoJava().addInterface(interfacePojo.getSimpleName());

        List<Method> metodosGetDeclarados = getMetodosRecursivo(interfacePojo, new ArrayList<>());

        for (Method metodo : metodosGetDeclarados) {

            //@Override
            //public default List<ItfPrevisaoValorMoeda> getParcelas() {
            //     return (List<ItfPrevisaoValorMoeda>) getValorPorReflexao();
            // }
            String metodoTipoRetorno = metodo.getReturnType().getSimpleName();
            String nomeMetodo = metodo.getName();
            if (!metodo.getReturnType().isPrimitive()) {
                getCodigoJava().addImport(metodo.getReturnType());
            }
            try {

                if (!getCodigoJava().hasMethodSignature(metodo.getName())) {
                    if (metodo.getName().startsWith("get")) {
                        StringBuilder metodoStr = new StringBuilder();
                        metodoStr.append("public default ");
                        metodoStr.append(metodoTipoRetorno);
                        metodoStr.append(" ");
                        metodoStr.append(nomeMetodo);

                        metodoStr.append("(){ \n return (");
                        metodoStr.append(metodoTipoRetorno);
                        metodoStr.append(") getValorPorReflexao(); \n } \n");

                        System.out.println("Gerou:");
                        System.out.println(metodoStr.toString());
                        getCodigoJava().addMethod(metodoStr.toString()).
                                addAnnotation(Override.class);
                    }
                    if (metodo.getName().startsWith("is")) {
                        StringBuilder metodoStr = new StringBuilder();
                        metodoStr.append("public default ");
                        metodoStr.append(metodoTipoRetorno);
                        metodoStr.append(" ");
                        metodoStr.append(nomeMetodo);

                        metodoStr.append("(){ \n return (");
                        metodoStr.append(metodoTipoRetorno);
                        metodoStr.append(") getValorPorReflexao(); \n } \n");

                        System.out.println("Gerou:");
                        System.out.println(metodoStr.toString());
                        getCodigoJava().addMethod(metodoStr.toString()).
                                addAnnotation(Override.class);
                    }
                }

            } catch (Throwable y) {
                System.out.println("Erro criando método" + y.getMessage());
            }

        }

    }

    private boolean isInterfaceElegivel(Class pInterface) {
        return (!pInterface.getSimpleName().equals(ItfBeanGenericoSomenteLeitura.class.getSimpleName())
                && !pInterface.getSimpleName().equals(Object.class.getSimpleName())
                && !pInterface.getSimpleName().equals(ItfBeanVisualizavel.class.getSimpleName())
                && !pInterface.getSimpleName().equals(ItfBeanInstanciado.class.getSimpleName())
                && !pInterface.getSimpleName().equals(ItfBeanGenerico.class.getSimpleName())
                && !pInterface.getSimpleName().equals(ItfBeanGenericoSomenteLeitura.class.getSimpleName())
                && !pInterface.getSimpleName().equals(ItfBeanReflexoes.class.getSimpleName()))
                && !pInterface.getSimpleName().equals(ItfBeanComUploadImagensPadrao.class.getSimpleName())
                && !pInterface.getSimpleName().equals(ItfBeanComUploadImagemGrande.class.getSimpleName())
                && !pInterface.getSimpleName().equals(ItfBeanComImagemGrande.class.getSimpleName())
                && !pInterface.getSimpleName().equals(ItfBeanComImagemMedio.class.getSimpleName())
                && !pInterface.getSimpleName().equals(ItfBeanComImagemPequena.class.getSimpleName())
                && !pInterface.getSimpleName().equals(ItfBeanComUploadImagemPequena.class.getSimpleName());

    }

    private void adicionarMetodos(Class classeAnalizada, List<Method> metodosEncontrados) {
        Method[] metodos = classeAnalizada.getDeclaredMethods();

        for (Method metodo : metodos) {
            if (metodo.getName().equals("getJustificativa")) {
                System.out.println("Te peguei!");
            }
            if (metodo.isDefault()) {
                continue;
            }
            if (!metodo.getName().startsWith("get") && !metodo.getName().startsWith("is")) {
                continue;
            }

            if (!metodosEncontrados.stream().filter(mt -> mt.getName().equals(metodo.getName())).findFirst().isPresent()) {
                metodosEncontrados.add(metodo);
            }

        }
    }

    public List<Method> getMetodosRecursivo(Class pInterface, List<Method> metodosEncontrados) {
        Class[] classes = pInterface.getInterfaces();
        if (!isInterfaceElegivel(pInterface)) {
            return metodosEncontrados;
        }
        adicionarMetodos(pInterface, metodosEncontrados);
        for (Class classeAnalizada : classes) {
            if (!isInterfaceElegivel(classeAnalizada)) {
                continue;
            }
            adicionarMetodos(classeAnalizada, metodosEncontrados);
            System.out.println("Analizando" + classeAnalizada.getSimpleName());
            metodosEncontrados.addAll(getMetodosRecursivo(classeAnalizada, metodosEncontrados));
        }

        return metodosEncontrados;
    }
}
