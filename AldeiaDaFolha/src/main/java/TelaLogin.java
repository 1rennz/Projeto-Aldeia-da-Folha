import javax.swing.*;
import java.awt.*;

public class TelaLogin {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> mostrarFormulario());
    }

    public static void mostrarFormulario() {
        JFrame janela = new JFrame("Tela de login");

        janela.setSize(600, 400);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);

        JPanel formulario = new JPanel(new GridLayout(0, 1));

        JTextField campoEmail = new JTextField(20);
        JPasswordField campoSenha = new JPasswordField(20);
        JTextField campoCPF = new JTextField(20);
        JButton botaoEnviar = new JButton("Enviar");

        JLabel labelEmail = new JLabel("Email:");
        JLabel labelSenha = new JLabel("Senha:");
        JLabel labelCPF = new JLabel("CPF:");

        Dimension tamanhoRotulo = new Dimension(45, 20);
        labelEmail.setPreferredSize(tamanhoRotulo);
        labelSenha.setPreferredSize(tamanhoRotulo);
        labelCPF.setPreferredSize(tamanhoRotulo);

        JPanel linhaEmail = new JPanel(new BorderLayout(7, 0));
        linhaEmail.add(labelEmail, BorderLayout.WEST);
        linhaEmail.add(campoEmail);

        JPanel linhaSenha = new JPanel(new BorderLayout(7, 0));
        linhaSenha.add(labelSenha, BorderLayout.WEST);
        linhaSenha.add(campoSenha);

        JPanel linhaCPF = new JPanel(new BorderLayout(7, 0));
        linhaCPF.add(labelCPF, BorderLayout.WEST);
        linhaCPF.add(campoCPF);

        JPanel linhaBotao = new JPanel(new FlowLayout(FlowLayout.LEFT, 52, 2));
        linhaBotao.add(botaoEnviar);

        formulario.add(linhaEmail);
        formulario.add(linhaSenha);
        formulario.add(linhaCPF);
        formulario.add(linhaBotao);

        janela.setLayout(new GridBagLayout());
        janela.add(formulario);

        botaoEnviar.addActionListener(evento -> {
            String email = campoEmail.getText();
            String cpf = campoCPF.getText();
            String senha = new String(campoSenha.getPassword());

            JOptionPane.showMessageDialog(janela, "login cadastrado!");
        });
    }
}