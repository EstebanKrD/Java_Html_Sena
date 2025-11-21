// MotorIA.java
// Clase auxiliar con métodos estáticos usados por MainApp

public class MotorIA {

    // Método para generar OTP de 6 dígitos
    public static String generarOTP() {
        int codigo = (int) (Math.random() * 900000) + 100000; // 100000..999999
        return String.valueOf(codigo);
    }

    // Método para clasificar un gasto (recibido como descripción)
    public static String clasificarGasto(String descripcion) {
        if (descripcion == null) return "Otros";
        descripcion = descripcion.toLowerCase();

        if (descripcion.contains("comida") || descripcion.contains("restaurante") || descripcion.contains("mercado")) {
            return "Gasto Variable";
        }
        if (descripcion.contains("arriendo") || descripcion.contains("luz") || descripcion.contains("agua")
                || descripcion.contains("internet") || descripcion.contains("telefonia") || descripcion.contains("servicio")) {
            return "Gasto Fijo";
        }
        if (descripcion.contains("ropa") || descripcion.contains("ocio") || descripcion.contains("cine")) {
            return "Gasto Extra";
        }
        // valor por defecto
        return "Gasto Extra / No Clasificado";
    }

    // Método para evaluar riesgo del gasto respecto a un presupuesto
    public static String evaluarRiesgo(double valor, double presupuesto) {
        if (presupuesto <= 0) return "Desconocido (presupuesto inválido)";
        double porcentaje = (valor / presupuesto) * 100.0;

        if (porcentaje < 10.0) return "Bajo (gasto controlado)";
        if (porcentaje < 30.0) return "Medio (precaución)";
        if (porcentaje < 50.0) return "Alto (revisa tus gastos)";
        return "Crítico (este gasto puede desbalancear tu presupuesto)";
    }

    // Detecta categoría general de un mensaje (usado por la IA conversacional)
    public static String detectarCategoria(String mensaje) {
        if (mensaje == null) return "general";
        String m = mensaje.toLowerCase();
        if (m.contains("ahorrar") || m.contains("ahorro") || m.contains("guardar")) return "ahorro";
        if (m.contains("ingreso") || m.contains("sueldo") || m.contains("salario") || m.contains("freelance")) return "ingresos";
        if (m.contains("gasto") || m.contains("compr") || m.contains("pago")) return "gasto";
        if (m.contains("deuda") || m.contains("credito") || m.contains("tarjeta")) return "deudas";
        if (m.contains("presupuesto")) return "presupuesto";
        return "general";
    }

    // Genera una respuesta textual según la categoría detectada
    public static String generarRespuesta(String categoria) {
        switch (categoria) {
            case "ahorro":
                return "Te sugiero reservar al menos un 10% de tus ingresos como ahorro.";
            case "ingresos":
                return "Puedes aumentar ingresos con trabajo freelance, ventas o cursos.";
            case "gasto":
                return "Revisa tus gastos variables y registra cada compra para controlarlos.";
            case "deudas":
                return "Prioriza pagar deudas con mayor interés y evita nuevas deudas.";
            case "presupuesto":
                return "Divide tu presupuesto en: gastos fijos, variables y ahorro.";
            default:
                return "Puedo ayudarte a revisar presupuesto, gastos o sugerir ahorros. ¿Qué quieres hacer?";
        }
    }
}
