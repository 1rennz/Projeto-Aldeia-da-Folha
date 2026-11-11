// Importa os componentes da interface Swing.
import javax.swing.*;

// Importa layouts e dimensões do AWT.
import java.awt.*;

// Declara a classe principal da tela.
public class TelaNinja {

    // Ponto de entrada do programa.
    public static void main(String[] args) {
        // Inicia a interface na fila de eventos do Swing.
        SwingUtilities.invokeLater(() -> mostrarFormulario());
    }

    // Cria e exibe a janela do formulário.
    public static void mostrarFormulario() {

        // Cria a janela.
        JFrame janela = new JFrame("Tela de login");

        // Define o tamanho da janela.
        janela.setSize(700, 400);

        // Encerra o programa ao fechar a janela.
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Centraliza a janela na tela.
        janela.setLocationRelativeTo(null);

        // Cria o painel principal e organiza seus componentes verticalmente.
        JPanel formulario = new JPanel();
        formulario.setLayout(new BoxLayout(formulario, BoxLayout.Y_AXIS));

        // Cria os campos do formulário.
        JTextField campoCla = new JTextField(20);
        JTextField campoID = new JTextField(20);
        JTextField campoEstiloLuta = new JTextField(20);

        // Cria o botão de envio.
        JButton botaoEnviar = new JButton("Enviar");

        // Cria os rótulos dos campos.
        JLabel labelCla = new JLabel("Clã:");
        JLabel labelID = new JLabel("ID:");
        JLabel labelEstiloLuta = new JLabel("Estilo de luta:");

        // Define uma largura comum para alinhar os campos.
        labelCla.setHorizontalAlignment(SwingConstants.CENTER);
        labelID.setHorizontalAlignment(SwingConstants.CENTER);
        labelEstiloLuta.setHorizontalAlignment(SwingConstants.CENTER);

        // Cria a linha do campo Clã.
        JPanel linhaCla = new JPanel(new GridLayout(0, 1, 0, 3));
        linhaCla.add(labelCla, BorderLayout.WEST);
        linhaCla.add(campoCla);

        // Cria a linha do campo ID.
        JPanel linhaID = new JPanel(new GridLayout(0, 1, 0, 3));
        linhaID.add(labelID, BorderLayout.WEST);
        linhaID.add(campoID);

        // Cria a linha do campo Estilo de luta.
        JPanel linhaEstiloLuta = new JPanel(new GridLayout(0, 1, 0, 3));
        linhaEstiloLuta.add(labelEstiloLuta, BorderLayout.WEST);
        linhaEstiloLuta.add(campoEstiloLuta);

        // Cria o botão que abre e recolhe as patentes.
        JButton botaoPatente = new JButton("Patente");

        // Define as opções da lista de patentes.
        String[] patentes = {"Junin", "Genin", "Kage", "Hokage"};

        // Cria a lista de patentes.
        JList<String> listaPatentes = new JList<>(patentes);
        DefaultListCellRenderer alinhamentoLista = new DefaultListCellRenderer();
        alinhamentoLista.setHorizontalAlignment(SwingConstants.CENTER);
        listaPatentes.setCellRenderer(alinhamentoLista);

        // Permite selecionar mais de uma patente.
        listaPatentes.setSelectionMode(
                ListSelectionModel.MULTIPLE_INTERVAL_SELECTION

        );

        // Mostra até quatro opções ao mesmo tempo.
        listaPatentes.setVisibleRowCount(4);

        // Adiciona a lista a uma área com rolagem.
        JScrollPane listaComRolagem = new JScrollPane(listaPatentes);
        listaComRolagem.setPreferredSize(new Dimension(110, 100));

        // Cria o painel das opções e deixa-o oculto inicialmente.
        JPanel opcoesPatente = new JPanel(new FlowLayout(FlowLayout.CENTER));
        opcoesPatente.add(listaComRolagem);
        opcoesPatente.setVisible(false);

        // Cria uma linha para o botão Patente.
        JPanel linhaBotaoPatente = new JPanel(
                new FlowLayout(FlowLayout.CENTER)
        );
        linhaBotaoPatente.add(botaoPatente);

        // Agrupa o botão Patente e a lista.
        JPanel linhaNiveis = new JPanel();
        linhaNiveis.setLayout(new BoxLayout(linhaNiveis, BoxLayout.Y_AXIS));
        linhaNiveis.add(linhaBotaoPatente);
        linhaNiveis.add(opcoesPatente);

        // Mostra ou recolhe a lista ao clicar no botão Patente.
        botaoPatente.addActionListener(evento -> {
            opcoesPatente.setVisible(!opcoesPatente.isVisible());
            linhaNiveis.revalidate();
            linhaNiveis.repaint();
        });

        // Cria a linha do botão Enviar.
        JPanel linhaBotao = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 8, 0)
        );
        linhaBotao.add(botaoEnviar);

        // Adiciona as linhas ao formulário, na ordem desejada.
        formulario.add(linhaCla);
        formulario.add(linhaID);
        formulario.add(linhaEstiloLuta);
        formulario.add(linhaNiveis);
        formulario.add(linhaBotao);

        // Centraliza o formulário na janela.
        janela.setLayout(new GridBagLayout());
        janela.add(formulario);

        // Define a ação executada ao clicar em Enviar.
        botaoEnviar.addActionListener(evento -> {
            // Lê o conteúdo digitado nos campos.
            String cla = campoCla.getText();
            String id = campoID.getText();
            String estiloLuta = campoEstiloLuta.getText();

            // Mostra uma mensagem de confirmação.
            JOptionPane.showMessageDialog(janela, "Cadastro enviado!");
        });

        // Exibe a janela depois de montar os componentes.
        janela.setVisible(true);
    }
}