package view;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Totalizador;
import service.TotalizadorService;

public class TelaTotalizadores extends JFrame {

    private JButton btnGerar;
    private JTable tblTotais;
    private DefaultTableModel model;

    private TotalizadorService service = new TotalizadorService();

    public TelaTotalizadores() {
        setTitle("Totalizadores");
        setSize(560, 460);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        JLabel lbl = new JLabel("Clique em Gerar para calcular e salvar os totalizadores");
        lbl.setBounds(20, 20, 400, 25);
        add(lbl);

        btnGerar = new JButton("Gerar");
        btnGerar.setBounds(420, 18, 100, 30);
        add(btnGerar);

        model = new DefaultTableModel();
        model.addColumn("Descrição");
        model.addColumn("Quantidade");
        model.addColumn("Data geração");
        tblTotais = new JTable(model);
        tblTotais.getColumnModel().getColumn(0).setPreferredWidth(260);
        JScrollPane scroll = new JScrollPane(tblTotais);
        scroll.setBounds(20, 60, 500, 340);
        add(scroll);

        btnGerar.addActionListener(e -> gerar());
    }

    private void gerar() {
        model.setRowCount(0);
        try {
            ArrayList<Totalizador> totais = service.gerar();
            for (Totalizador t : totais) {
                model.addRow(new Object[] {t.getDescricao(), t.getQuantidade(), t.getDataGeracao()});
            }
            JOptionPane.showMessageDialog(this, "Totalizadores gerados e salvos no banco!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao gerar: " + ex.getMessage());
        }
    }
}
