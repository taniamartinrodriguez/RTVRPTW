package top;

/**
 * \brief Punto de entrada del recomendador turístico.
 *
 * Sin argumentos conserva el procesamiento original por lotes. Con argumentos procesa una sola instancia de manera rápida.
 */
public class mainTOPTW {

    /**
     * \brief Inicia el algoritmo para una instancia concreta o para todas las originales.
     */
    public static void main(String[] args) {
        if (args.length > 0 && "--resumen".equals(args[0])) {
            if (args.length != 2) {
                throw new IllegalArgumentException("Uso: --resumen nombre-instancia.txt");
            }
            TOPTW problem = TOPTWReader.readProblem("Instances/TOPTW/" + args[1]);
            System.out.print(TOPTWInstanceSummary.summarize(problem));
            return;
        }
        if (args.length > 0) {
            String instance = args[0];
            int iterations = args.length > 1 ? Integer.parseInt(args[1]) : 5;
            int rcl = args.length > 2 ? Integer.parseInt(args[2]) : 3;
            TOPTW problem = TOPTWReader.readProblem("Instances/TOPTW/" + instance);
            TOPTWGRASP grasp = new TOPTWGRASP(new TOPTWSolution(problem));
            System.out.println(" --> Instance: " + instance);
            grasp.GRASP(iterations, rcl);
            return;
        }

        String[] instances = new String[29];
        
        instances[0] = "c101.txt"; instances[3] = "c104.txt"; instances[6] = "c107.txt";
        instances[1] = "c102.txt"; instances[4] = "c105.txt"; instances[7] = "c108.txt";
        instances[2] = "c103.txt"; instances[5] = "c106.txt"; instances[8] = "c109.txt"; 

        instances[9] = "r101.txt";  instances[12] = "r104.txt"; instances[15] = "r107.txt";
        instances[10] = "r102.txt"; instances[13] = "r105.txt"; instances[16] = "r108.txt";
        instances[11] = "r103.txt"; instances[14] = "r106.txt"; instances[17] = "r109.txt";
        instances[18] = "r110.txt"; instances[19] = "r111.txt"; instances[20] = "r112.txt";
        
        instances[21] = "rc101.txt"; instances[24] = "rc104.txt"; instances[27] = "rc107.txt";
        instances[22] = "rc102.txt"; instances[25] = "rc105.txt"; instances[28] = "rc108.txt";
        instances[23] = "rc103.txt"; instances[26] = "rc106.txt"; 
        
        for(int i = 0; i < instances.length; i++) {
            String INSTANCE = "Instances/TOPTW/"+instances[i];
            TOPTW problem = TOPTWReader.readProblem(INSTANCE);
            TOPTWSolution solution = new TOPTWSolution(problem);
            TOPTWGRASP grasp = new TOPTWGRASP(solution);

            System.out.println(" --> Instance: "+instances[i]);
            grasp.GRASP(10000, 3);
            grasp.GRASP(10000, 5);
            grasp.GRASP(10000, 7);
            System.out.println("");
        }
    }
    
}
