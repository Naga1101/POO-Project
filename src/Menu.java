import java.io.Serializable;
import java.util.*;
import java.text.DecimalFormat;

public class Menu implements Serializable {

    // Interfaces auxiliares

    /** Functional interface para handlers. */
    public interface Handler {  // método de tratamento
        public void execute();
    }

    /** Functional interface para pré-condições. */
    /** Podia ser utilizado Predicate<T> */
    public interface PreCondition {  
        public boolean validate();
    }

    // Varíável de classe para suportar leitura

    private static Scanner is = new Scanner(System.in);

    private List<String> opcoes;            // Lista de opções
    private List<PreCondition> disponivel;  // Lista de pré-condições
    private List<Handler> handlers;         // Lista de handlers
    private User userOnline = null;

    public Menu(String[] opcoes) {
        this.opcoes = Arrays.asList(opcoes);
        this.disponivel = new ArrayList<>();
        this.handlers = new ArrayList<>();
        this.opcoes.forEach(s-> {
            this.disponivel.add(()->true);
            this.handlers.add(()->System.out.println("\nATENÇÃO: Opção não implementada!"));
        });
    }

    public void run() {
        int op;
        do {
            show();
            op = readOption();
            // testar pré-condição
            if (op>0 && !this.disponivel.get(op-1).validate()) {
                System.out.println("Opção indisponível! Tente novamente.");
            } else if (op>0) {
                // executar handler
                this.handlers.get(op-1).execute();
            }
        } while (op != 0);
    }

    /**
     * Método para registar um handler numa opção do Menu.
     *
     * @param i indice da opção  (começa em 1)
     * @param h handlers a registar
     */
    public void setHandler(int i, Handler h) {
        this.handlers.set(i-1, h);
    }
    
    public void setPreCondition(int i, PreCondition b) {
        this.disponivel.set(i-1,b);
    }
    // Métodos auxiliares

    /** Apresentar o Menu */
    private void show() {
        if(userOnline != null){
            DecimalFormat df = new DecimalFormat("#.##");
            System.out.println("\n");
            System.out.println("Utilizador atualmente online " + userOnline.getNome() + "| Carteira " + df.format(userOnline.getSaldo()) 
            + "€ " + "| Valor Total Recebido " + df.format(userOnline.getValorRecebido()) + "€ ");
        }
        System.out.println("\n *** Menu *** ");
        for (int i=0; i<this.opcoes.size(); i++) {
            System.out.print(i+1);
            System.out.print(" - ");
            System.out.println(this.disponivel.get(i).validate()?this.opcoes.get(i):"---");
        }
        System.out.println("0 - Sair");
    }

    /** Ler uma opção válida */
    private int readOption() {
        int op;
        //Scanner is = new Scanner(System.in);

        System.out.print("Opção: ");
        try {
            String line = is.nextLine();
            op = Integer.parseInt(line);
        }
        catch (NumberFormatException e) { // Não foi escrito um int
            op = -1;
        }
        if (op<0 || op>this.opcoes.size()) {
            System.out.println("Opção Inválida!!!");
            op = -1;
        }
        return op;
    }
    
    public void setUserOnline(User userOnline) {
        User user = userOnline;
        this.userOnline = user;
    }
}
