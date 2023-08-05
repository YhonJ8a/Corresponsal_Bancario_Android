package com.newpos.corresponsal.variables;

import com.newpos.corresponsal.ConsultarCliente;
import com.newpos.corresponsal.ConsultarCorresponsal;
import com.newpos.corresponsal.CorrConsultarSaldo;
import com.newpos.corresponsal.CorrHistorialTransaccional;
import com.newpos.corresponsal.CorrPagoConTarjeta;
import com.newpos.corresponsal.CorrRetiros;
import com.newpos.corresponsal.CorresponsalDepocitos;
import com.newpos.corresponsal.CorresponsalTransferencia;
import com.newpos.corresponsal.CrearCliente;
import com.newpos.corresponsal.ListaCorresponsales;
import com.newpos.corresponsal.ListadoClientes;
import com.newpos.corresponsal.NuevoCorresponsal;


public class Constantes {

    public static final String IMG_TARJETA = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSoCpIDf9Xcmaw5cZoIMnNFzGvZF0x6_h7A8Wpull_s_p9YcODJzTIx9JiZObz2pyK0YLA&usqp=CAU";

//vista admin

    public static final String AGREGAR_USUARIO = "https://cdn1.iconfinder.com/data/icons/users-39/24/302-256.png";
    public static final Class VISTA_AU = CrearCliente.class;

    public static final String AGREGAR_CORRESPONSAL = "https://cdn1.iconfinder.com/data/icons/real-estate-25/24/Add-House-256.png";
    public static final Class VISTA_AC = NuevoCorresponsal.class;

    public static final String CONSULTAR_CLIENTE = "https://cdn1.iconfinder.com/data/icons/office-322/24/person-user-search-find-avatar-256.png";
    public static final Class VISTA_CCli = ConsultarCliente.class;

    public static final String CONSULTAR_CORRESPÓNSAL = "https://cdn0.iconfinder.com/data/icons/real-estate-289/60/search__house__building__realestate__property-256.png";
    public static final Class VISTA_Cor = ConsultarCorresponsal.class;

    public static final String LISTA_CLIENTES = "https://cdn0.iconfinder.com/data/icons/phosphor-thin-vol-5/256/user-list-thin-256.png";
    public static final Class VISTA_LCli = ListadoClientes.class;

    public static final String LISTA_CORRESPONSAL = "https://cdn4.iconfinder.com/data/icons/stay-at-home-36/512/To_do_List_1-128.png";
    public static final Class VISTA_LCor = ListaCorresponsales.class;


//    imagenes para la vista canselar

    public static final String IMAGEN_CANSELAR_NEGATIVA = "https://cdn-icons-png.flaticon.com/512/84/84045.png";
    public static final String IMAGEN_CANSELAR_POSITIVA = "https://img2.freepng.es/20180315/djw/kisspng-check-mark-computer-icons-clip-art-green-tick-mark-5aab1c5116d0a0.2098334515211633450935.jpg" ;


//    vista corresponsal


    public static final String PAGO_TARJETA = "https://cdn1.iconfinder.com/data/icons/banking-36/128/transaction_card_payment-512.png";
    public static final Class VISTA_PCT = CorrPagoConTarjeta.class;

    public static final String RETIROS = "https://cdn4.iconfinder.com/data/icons/aami-web-internet/64/aami6-06-256.png";
    public static final Class VISTA_R = CorrRetiros.class;

    public static final String DEPOCITOS = "https://cdn0.iconfinder.com/data/icons/ticket-machine/500/ticket-machine_10-256.png";
    public static final Class VISTA_D = CorresponsalDepocitos.class;

    public static final String TRANSFERENCIAS = "https://cdn4.iconfinder.com/data/icons/digital-banking-and-finance/64/E-Transfer-mobile-transaction-banking-256.png";
    public static final Class VISTA_T = CorresponsalTransferencia.class;

    public static final String HISTORIAL_TRANSACCIONES = "https://cdn0.iconfinder.com/data/icons/food-delivery-outline-stay-home/512/Order_history-256.png";
    public static final Class VISTA_HT = CorrHistorialTransaccional.class;

    public static final String CONSULTA_SALDO = "https://cdn1.iconfinder.com/data/icons/aami-web-internet/64/aami2-67-256.png";
    public static final Class VISTA_CS = CorrConsultarSaldo.class;

}
