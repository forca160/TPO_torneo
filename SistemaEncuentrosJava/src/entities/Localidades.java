package entities;

public enum Localidades {

    AGRONOMIA(-34.6018, -58.4807),
    ALMAGRO(-34.6044, -58.4210),
    BALVANERA(-34.6107, -58.4023),
    BARRACAS(-34.6379, -58.3704),
    BELGRANO(-34.5600, -58.4560),
    BOEDO(-34.6249, -58.4130),
    CABALLITO(-34.6185, -58.4433),
    CHACARITA(-34.5877, -58.4565),
    COGHLAN(-34.5631, -58.4759),
    COLEGIALES(-34.5831, -58.4511),
    CONSTITUCION(-34.6278, -58.3819),
    FLORES(-34.6335, -58.4599),
    FLORESTA(-34.6301, -58.4866),
    LA_BOCA(-34.6359, -58.3610),
    LA_PATERNAL(-34.5980, -58.4584),
    LINIERS(-34.6468, -58.5322),
    MATADEROS(-34.6541, -58.5068),
    MONSERRAT(-34.6084, -58.3726),
    MONTE_CASTRO(-34.6186, -58.5021),
    NUEVA_POMPEYA(-34.6521, -58.4229),
    NUNEZ(-34.5448, -58.4618),
    PALERMO(-34.5837, -58.4255),
    PARQUE_AVELLANEDA(-34.6434, -58.4881),
    PARQUE_CHACABUCO(-34.6286, -58.4306),
    PARQUE_PATRICIOS(-34.6276, -58.4019),
    PUERTO_MADERO(-34.6083, -58.3640),
    RECOLETA(-34.5885, -58.3973),
    RETIRO(-34.5930, -58.3753),
    SAAVEDRA(-34.5470, -58.4892),
    SAN_CRISTOBAL(-34.6193, -58.4126),
    SAN_NICOLAS(-34.6044, -58.3816),
    SAN_TELMO(-34.6231, -58.3731),
    VELEZ_SARSFIELD(-34.6375, -58.4909),
    VERSALLES(-34.6297, -58.5085),
    VILLA_CRESPO(-34.5984, -58.4410),
    VILLA_DEL_PARQUE(-34.5987, -58.4890),
    VILLA_DEVOTO(-34.5968, -58.5093),
    VILLA_GENERAL_MITRE(-34.6038, -58.4771),
    VILLA_LUGANO(-34.6745, -58.4792),
    VILLA_LURO(-34.6411, -58.4908),
    VILLA_ORTUZAR(-34.5870, -58.4587),
    VILLA_PUEYRREDON(-34.5846, -58.5072),
    VILLA_REAL(-34.6154, -58.5130),
    VILLA_RIACHUELO(-34.6641, -58.4736),
    VILLA_SANTA_RITA(-34.6090, -58.4859),
    VILLA_SOLDATI(-34.6705, -58.4561),
    VILLA_URQUIZA(-34.5739, -58.4851);

    private final double latitud;
    private final double longitud;

    Localidades(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    /** Latitud del centro aproximado del barrio. */
    public double getLatitud() {
        return latitud;
    }

    /** Longitud del centro aproximado del barrio. */
    public double getLongitud() {
        return longitud;
    }
}
