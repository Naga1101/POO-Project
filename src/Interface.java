import java.io.Serializable;
 import java.time.LocalDate;
 import java.util.ArrayList;
 import java.util.Scanner;

public class Interface implements Serializable {
  private User utilizador;
  private Transportadoras transportadora;
  private User userOnline;
  private VintageMain main;
  private int id = 0;
  private int idTransportadora = 0;
  
  private Scanner sc;
  
  public Interface() {
    this.utilizador = new User();
    sc = new Scanner(System.in);
  }

    public Interface(VintageMain main) {
        this.utilizador = new User();
        this.transportadora = new Transportadoras();
        this.main = main;
        sc = new Scanner(System.in);
    }
  
  
  /**
   * Método que executa o menu principal.
   * Coloca a interface em execução.
   */
  
  public void run() {
    System.out.println("\n");
    System.out.println("Data atual: " + main.getData());
    
    Menu menu = new Menu(new String[] {
            " Login ",
            " Registar ",
            " Criar Transportadora",
            " Lista de users",
            " Lista de Transportadoras",
            " Avançar no tempo "
    });
    menu.setHandler(1, () -> insereLogin());
    menu.setHandler(2, () -> criarUtilizador());
    menu.setHandler(3, () -> criarTransportadora());
    menu.setHandler(4, () -> main.imprimirListaUsers());
    menu.setHandler(5, () -> main.imprimirListaTransportadoras());
    menu.setHandler(6, () -> mudarTempo());
    
    menu.run();
  }
  
  /**
   * Métodos associados ao handler
   */
  private void gestaoMenuUtilizador() {
    Menu menuUtilizador = new Menu(new String[] {"Procurar Artigo", 
                            "Listar Artigo", 
                            "Parar de anunciar Artigo",
                            "Ver carrinho"});
    
    System.out.println("\n");
    System.out.println("Utilizador atualmente online " + userOnline.getNome());
    //registar os handlers
    menuUtilizador.setHandler(1, () -> addListaCompra());
    menuUtilizador.setHandler(2, () -> gestaoMenuVenda());
    menuUtilizador.setHandler(3, () -> remArtigoLV());
    menuUtilizador.setHandler(4, () -> visualizarCarrinho());
    
    menuUtilizador.run();
  }
  
  private void gestaoMenuVenda() {
    Menu menuUtilizador = new Menu(new String[] {"Vender T-Shirts",
                             "Vender Malas",
                             "Vender Sapatilhas"});
                            
    //registar os handlers
    menuUtilizador.setHandler(1, () -> addListaVenda("TShirt"));
    menuUtilizador.setHandler(2, () -> addListaVenda("Mala"));
    menuUtilizador.setHandler(3, () -> addListaVenda("Sapatilhas"));
    
    menuUtilizador.run();
  }
  
  private void insereLogin(){
      userOnline = null;
      ArrayList<User> listaUsers = main.getlistaUsersRegistados();
    System.out.println("Escreva o seu email: ");
    String login = sc.nextLine();

    for(User user : listaUsers){
        if(user.getEmail().equals(login)){
            this.userOnline = user;
            break;
        }
      }

    if(userOnline != null){
        gestaoMenuUtilizador();
    }
    else{
        System.out.println("O utilizador nao existe, por favor registe -se.");
    }
  } 
  
  private void criarUtilizador() {   
    id++;
    this.utilizador.setId(id);
    System.out.println("Escreva o seu email: ");
    String email = sc.nextLine();
    this.utilizador.setEmail(email);
    System.out.println("Escreva o seu nome: ");
    String nome = sc.nextLine();
    this.utilizador.setNome(nome);
    System.out.println("Escreva a sua morada: ");
    String morada = sc.nextLine();
    this.utilizador.setMorada(morada);
    System.out.println("Escreva o seu nif: ");
    int nif = sc.nextInt();
    sc.nextLine();
    this.utilizador.setNif(nif);

    main.addUsers(utilizador);
    System.out.println("Utilizador registado.");
  }

  private void criarTransportadora() {
    idTransportadora++;
    this.transportadora.setId(idTransportadora);
    System.out.println("Escreva o nome da transportadora: ");
    String nome = sc.nextLine();
    this.transportadora.setNome(nome);
    System.out.println("Escolha a margem de lucro: ");
    double margemLucro = sc.nextDouble();
    sc.nextLine();
    this.transportadora.setMargemLucro(margemLucro);
    System.out.println("Escolha o imposto da transportadora: ");
    double imposto = sc.nextDouble();
    sc.nextLine();
    this.transportadora.setImposto(imposto);

    System.out.println("A transportadora é exclusiva?");
    boolean respostaValida = false;
    while(!respostaValida){
        System.out.println("Responda: S | N");
        char pre = sc.next().charAt(0);
        sc.nextLine();
        if(pre == 'S') {
            respostaValida = true;
            this.transportadora.setPremium(true);
        }
        else if(pre == 'N') {
            respostaValida = true;
            this.transportadora.setPremium(false);
        }
        else{
            System.out.println("Caracter inválido.");
        }
    }


    main.addTransportadora(transportadora);
    System.out.println("Transportadora registada.");
  }

  private void addListaCompra() {
          int vazio = main.imprimirListaArtigosPublicados();
          if(vazio == 1){
              System.out.println("De momento não existe nenhum anûncio ativo");
              System.out.println("Por favor volte mais tarde.");
          }
          else{
              System.out.println("Que artigo pretende comprar? ");
              boolean existeLV = false;
              while(!existeLV){
                  System.out.println("Digite o seu código ou 0 caso queira voltar atrás: ");
                  int codigo = sc.nextInt();
                  sc.nextLine();
                  existeLV = userOnline.existeArtigoComCodigoLV(codigo);
                  if(codigo == 0){
                      System.out.println("Não adicionou nenhum artigo ao seu carrinho de compras");
                      existeLV = true;
                  }
                  if(existeLV){
                      System.out.println("Não pode comprar o seu próprio artigo");
                  }
                  else{
                      boolean existeLC = userOnline.existeArtigoComCodigoLC(codigo);
                      if(!existeLC){
                          userOnline.adicionarArtigoCompra(main.procurarArtigo(codigo));
                          System.out.println("Adicionou o artigo com o código " + codigo + " ao seu carrinho");
                      }
                      else{
                          System.out.println("Não pode adicionar um artigo que já está no seu carrinho");
                      }
                  }
              }

          }
      
  }
  
  private void addListaVenda(String nome) {
      if(nome == "TShirt"){
          TShirts novo = new TShirts();
          novo.setNome(nome);
          listarTShirst(novo);
      }
      
      else if(nome == "Mala"){
          Malas novo = new Malas();
          novo.setNome(nome);
          listarMala(novo);
      }
      
      else{
          Sapatilhas novo = new Sapatilhas();
          novo.setNome(nome);
          listarSapatilhas(novo);
      }
  }

    private void listarTShirst(TShirts t){
        boolean respostaValida = false;
        System.out.println("Escreva uma breve descriçao sobre a T-Shirt que pretende vender: ");
        String descricao = sc.nextLine();
        t.setDescricao(descricao);
        System.out.println("Qual é a marca da T-Shirt que pretende vender: ");
        String marca = sc.nextLine();
        t.setMarca(marca);
        System.out.println("Digite um código maior que 0: ");
        int codigo = sc.nextInt();
        sc.nextLine();
        System.out.println("Que transportadora pretende utilizar: ");
        Transportadoras transportadora = escolheTransportadora();
        t.setTransportadora(transportadora);
        t.setCodigo(codigo);
        System.out.println("Qual é o preço que pretende pela T-Shirt: ");
        System.out.println("Tenha em conta que pode ser afetado pelo estado da T-Shirt.");
        double precoBase = sc.nextDouble();
        t.setPrecoBase(precoBase);
        System.out.println("Qual o ano de lançamento do produto?");
        int ano = sc.nextInt();
        sc.nextLine();
        t.setAnoLancamento(ano);
        System.out.println("A T-Shirt é nova?");
        boolean usado = false;
        while(!respostaValida){
            System.out.println("Responda: S | N");
            char uso = sc.next().charAt(0);
            if(uso == 'S') {
                respostaValida = true;
                usado = false;
                t.setUsado(usado);
            }
            else if(uso == 'N') {
                respostaValida = true;
                usado = true;
                t.setUsado(usado);
            }
            else{
                System.out.println("Caracter inválido.");
            }
        }
        if(usado){
            System.out.println("Quantos donos teve anteriormente?");
            int numDonos = sc.nextInt();
            t.setNumDonos(numDonos);
        }
        respostaValida = false;
        System.out.println("Qual é o tamanho da T-Shirt?");
        while(!respostaValida){
            System.out.println("Responda: S | M | L | X");
            char tamanho = sc.next().charAt(0);
            sc.nextLine();
            if(tamanho == 'S') {
                respostaValida = true;
                t.setTamanho("S");
            }
            else if(tamanho == 'M') {
                respostaValida = true;
                t.setTamanho("M");
            }
            else if(tamanho == 'L') {
                respostaValida = true;
                t.setTamanho("L");
            }
            else if(tamanho == 'X') {
                respostaValida = true;
                t.setTamanho("XL");
            }
            else{
                System.out.println("Escolha um tamanho válido.");
            }
        }
        respostaValida = false;
        System.out.println("Qual é o padrão da T-Shirt entre os seguintes?");
        while(!respostaValida){
            System.out.println("Liso | Riscas | Desenhos | Outro");
            String padrao = sc.nextLine();
            if(padrao.equals("Liso")) {
                respostaValida = true;
                t.setPadrao("liso");
            }
            else if(padrao.equals("Riscas")) {
                respostaValida = true;
                t.setPadrao("Riscas");
            }
            else if(padrao.equals("Desenhos")) {
                respostaValida = true;
                t.setPadrao("Desenhos");
            }
            else if(padrao.equals("Outro")) {
                respostaValida = true;
                t.setPadrao("outro");
            }
            else{
                System.out.println("Escolha um padrão válido.");
            }
        }
        double precoFinal = t.calcularPreco();
        t.setPrecoFinal(precoFinal);
        userOnline.adicionarArtigoVender(t);
        main.addArtigos(t);
    }
  
  private void listarMala(Malas m){
      boolean respostaValida = false;
      System.out.println("Escreva uma breve descriçao sobre a Mala que pretende vender: ");
      String descricao = sc.nextLine();
      m.setDescricao(descricao);
      System.out.println("Qual é a marca da Mala que pretende vender: ");
      String marca = sc.nextLine();
      m.setMarca(marca);
      System.out.println("Digite um código maior que 0: ");
      int codigo = sc.nextInt();
      m.setCodigo(codigo);
      System.out.println("Que transportadora pretende utilizar: ");
      Transportadoras transportadora = escolheTransportadora();
      m.setTransportadora(transportadora);
      System.out.println("Qual é o preço que pretende pela Mala: ");
      System.out.println("Tenha em conta que pode ser afetado pelo estado da Mala.");
      double precoBase = sc.nextDouble();
      m.setPrecoBase(precoBase);
      System.out.println("Qual o ano de lançamento do produto?");
      int ano = sc.nextInt();
      sc.nextLine();
      m.setAnoLancamento(ano);
      System.out.println("A Mala é nova?");
      boolean usado = false;
      while(!respostaValida){
          System.out.println("Responda: S | N");
          char uso = sc.next().charAt(0);
          if(uso == 'S') {
              respostaValida = true;
              usado = false;
              m.setUsado(usado);
          }
          else if(uso == 'N') {
              respostaValida = true;
              usado = true;
              m.setUsado(usado);
          }
          else{
              System.out.println("Caracter inválido.");
          }
      }
      if(usado){
          System.out.println("Quantos donos teve anteriormente?");
          int numDonos = sc.nextInt();
          m.setNumDonos(numDonos);
      }
      System.out.println("Qual é a altura da mala (cm)?");
      double altura = sc.nextDouble();
      m.setAltura(altura);
      System.out.println("Qual é a largura da mala (cm)?");
      double largura = sc.nextDouble();
      m.setLargura(largura);
      System.out.println("Qual é a comprimento da mala (cm)?");
      double comprimento = sc.nextDouble();
      sc.nextLine();
      m.setComprimento(comprimento);
      System.out.println("Qual é o material da mala?");
      String material = sc.nextLine();
      m.setMaterial(material);
      System.out.println("A Mala é um produto exclusivo?");
      respostaValida = false;
      boolean premium = false;
      while(!respostaValida){
          System.out.println("Responda: S | N");
          char pre = sc.next().charAt(0);
          sc.nextLine();
          if(pre == 'S') {
              respostaValida = true;
              premium = true;
              m.setEhPremium(premium);
          }
          else if(pre == 'N') {
              respostaValida = true;
              premium = false;
              m.setEhPremium(premium);
          }
          else{
              System.out.println("Caracter inválido.");
          }
      }
      double precoFinal = m.calcularPreco();
      m.setPrecoFinal(precoFinal);
      userOnline.adicionarArtigoVender(m);
      main.addArtigos(m);
  }
  
  private void listarSapatilhas(Sapatilhas s) {
      boolean respostaValida = false;
      System.out.println("Escreva uma breve descriçao sobre as Sapatilhas que pretende vender? ");
      String descricao = sc.nextLine();
      s.setDescricao(descricao);
      System.out.println("Qual é a marca das Sapatilhas que pretende vender? ");
      String marca = sc.nextLine();
      s.setMarca(marca);
      System.out.println("Digite um código maior que 0: ");
      int codigo = sc.nextInt();
      s.setCodigo(codigo);
      sc.nextLine();
      System.out.println("Que transportadora pretende utilizar: ");
      Transportadoras transportadora = escolheTransportadora();
      s.setTransportadora(transportadora);
      System.out.println("Qual é o preço que pretende pelas Sapatilhas? ");
      System.out.println("Tenha em conta que pode ser afetado pelo estado das Sapatilhas.");
      double precoBase = sc.nextDouble();
      s.setPrecoBase(precoBase);
      System.out.println("Qual o ano de lançamento do produto?");
      int ano = sc.nextInt();
      sc.nextLine();
      s.setAnoLancamento(ano);
      System.out.println("As Sapatilhas sao novas?");
      boolean usado = false;
      while (!respostaValida) {
          System.out.println("Responda: S | N");
          char uso = sc.next().charAt(0);
          if (uso == 'S') {
              respostaValida = true;
              usado = false;
              s.setUsado(usado);
          } else if (uso == 'N') {
              respostaValida = true;
              usado = true;
              s.setUsado(usado);
          } else {
              System.out.println("Caracter inválido.");
          }
      }
      if (usado) {
          System.out.println("Quantos donos teve anteriormente?");
          int numDonos = sc.nextInt();
          s.setNumDonos(numDonos);
      }
      System.out.println("Qual é o tamanho das sapatilhas?");
      double tamanho = sc.nextDouble();
      s.setTamanho(tamanho);
      sc.nextLine();
      System.out.println("Qual é a cor das sapatilhas?");
      String cor = sc.nextLine();
      s.setCor(cor);
      System.out.println("As sapatilhas possuem atacadores?");
      respostaValida = false;
      boolean atacadores = false;
      while (!respostaValida) {
          System.out.println("Responda: S | N");
          char atac = sc.next().charAt(0);
          if (atac == 'S') {
              respostaValida = true;
              atacadores = true;
              s.setAtacadores(atacadores);
          } else if (atac == 'N') {
              respostaValida = true;
              atacadores = false;
              s.setAtacadores(atacadores);
          } else {
              System.out.println("Caracter inválido.");
          }
      }
          System.out.println("As sapatilhas são um produto exclusivo?");
          respostaValida = false;
          boolean premium = false;
          while (!respostaValida) {
              System.out.println("Responda: S | N");
              char pre = sc.next().charAt(0);
              sc.nextLine();
              if (pre == 'S') {
                  respostaValida = true;
                  premium = true;
                  s.setEhPremium(premium);
              } else if (pre == 'N') {
                  respostaValida = true;
                  premium = false;
                  s.setEhPremium(premium);
              } else {
                  System.out.println("Caracter inválido.");
              }
          }
      double precoFinal = s.calcularPreco();
      s.setPrecoFinal(precoFinal);
          userOnline.adicionarArtigoVender(s);
      main.addArtigos(s);
  }

  private Transportadoras escolheTransportadora() {
      int vazio = main.imprimirListaTransportadoras();
      if (vazio == 1) {
          System.out.println("De momento não existem Transportadoras disponíveis");
      } else {
          System.out.println("Que transportadora pretende utilizar? ");
          Transportadoras existe = null;
          while (existe == null) {
              System.out.println("Digite o id da transportadora que quer selecionar 0 caso queira voltar atrás: ");
              int id = sc.nextInt();
              sc.nextLine();
              existe = main.procurarTransportadora(id);
              if (existe != null) {
                  return existe;
              } else {
                  System.out.println("O id que selecionou não existe na lista.");
              }
          }
      }
      return null;
  }
  
  private void remArtigoLV() {
      int vazio = userOnline.imprimirListaVendas();
      if(vazio == 1){
          System.out.println("De momento não têm nenhum anûncio ativo");
      }
      else{
          System.out.println("Que artigo pretende parar de anunciar? ");
          boolean existe = false;
          while(!existe){
              System.out.println("Digite o seu código ou 0 caso queira voltar atrás: ");
              int codigo = sc.nextInt();
              sc.nextLine();
              existe = userOnline.existeArtigoComCodigoLV(codigo);
              if(codigo == 0){
                  System.out.println("Não parou de anunciar nenhum artigo");
                  existe = true;
              }
              if(existe){
                  userOnline.removerArtigoVender(codigo);
                  System.out.println("Parou de anunciar o artigo com o código " + codigo);
              }
              else{
                  System.out.println("O código que selecionou não existe na lista.");
              }
          }

      }

  }

    private void remArtigoLC() {
        int vazio = userOnline.imprimirListaCompras();
        if(vazio == 1){
            System.out.println("De momento não têm nada no carrinho.");
        }
        else{
            System.out.println("Que artigo pretende remover do carrinho? ");
            boolean existe = false;
            while(!existe){
                System.out.println("Digite o seu código ou 0 caso queira voltar atrás: ");
                int codigo = sc.nextInt();
                sc.nextLine();
                existe = userOnline.existeArtigoComCodigoLC(codigo);
                if(codigo == 0){
                    System.out.println("Não removeu nenhum artigo do carrinho.");
                    existe = true;
                }
                if(existe){
                    userOnline.removerArtigoComprar(codigo);
                    System.out.println("Removeu o artigo com o código " + codigo);
                }
                else{
                    System.out.println("O código que selecionou não existe no carrinho.");
                }
            }

        }

    }

    public void criarEncomenda(){
      Encomenda e = new Encomenda();
      e.setEncomenda(userOnline.getListaCompras());
      e.setTamanho(userOnline.getListaCompras().size());
      e.setPreco(e.calcularPreçoEncomenda(userOnline.getListaCompras()));
      e.setEstado("Em precessamento...");
      e.setDataCriacao(main.getData());
      e.setDataEntrega(e.calcularDataEntrega());
      System.out.println(e.toString());
      System.out.println("Encomenda finalizada.");
      
      userOnline.adicionarEncomenda(e);
      
      main.pagarVendedor(userOnline.getListaCompras());
      main.removerAnuncio(userOnline.getListaCompras()); 
      main.realizarEncomenda(userOnline.getListaCompras());
      ArrayList<Artigos> novaLista = new ArrayList<>();
      userOnline.setListaCompras(novaLista);
    }



  public void visualizarCarrinho(){
      int vazio = userOnline.imprimirListaCompras();
      if(vazio == 1){
          System.out.println("De momento não têm nenhum artigo no carrinho");
      }
      else{
        menuCarrinho();
      }
  }

    private void menuCarrinho() {
        Menu menuUtilizador = new Menu(new String[] {"Finalizar encomenda",
                "Remover artigo"});

        //registar os handlers
        menuUtilizador.setHandler(1, () -> criarEncomenda());
        menuUtilizador.setHandler(2, () -> remArtigoLC());

        menuUtilizador.run();
    }

    private void mudarTempo(){
        System.out.println("Digite o número de semanas que pretende avançar: ");
        int semanas = sc.nextInt();
        sc.nextLine();
        main.setData(semanas);
        
        System.out.println("\n");
        System.out.println("Data atual: " + main.getData());
        
        main.verificarEncomenda();
    }
}
