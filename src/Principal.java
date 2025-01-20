import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese API KEY: ");
        String KEY = sc.nextLine();
        String menu = """
                ************* CONVERSION EXCHANGE *************
                1. USD -> COP
                2. USD -> ARS
                3. USD -> BRL
                4. SALIR
                ***********************************************
                Digite opción:
                """;
        APIConection api = new APIConection();
        int opc = 0;
        while(opc != 4){
            String fromCurrency = "USD";
            String toCurrency = "";
            System.out.print(menu);
            opc = sc.nextInt();
            toCurrency = switch (opc) {
                case 1 -> "COP";
                case 2 -> "ARS";
                case 3 -> "BRL";
                default -> toCurrency;
            };
            api.conectToAPI(KEY, fromCurrency, toCurrency);
        }
    }
}
