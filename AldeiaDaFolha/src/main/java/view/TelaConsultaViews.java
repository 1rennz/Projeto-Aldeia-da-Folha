package view;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.ViewDAO;

public class TelaConsultaViews extends JFrame {

    private JComboBox<String> cbView;
    private JButton btnConsultar;
    private JTable tblResultado;
    private DefaultTableModel model;

    public TelaConsultaViews() {
        setTitle("Consulta das Views");
        setSize(760, 480);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        JLabel lbl = new JLabel("View:");
        lbl.setBounds(20, 20, 50, 25);
        add(lbl);

        cbView = new JComboBox<String>(new String[] {
                "vw_ninja_missoes",
                "vw_total_ninjas_por_vila",
                "vw_total_missoes_por_rank",
                "vw_missoes_sem_ninjas"});
        cbView.setBounds(70, 20, 250, 25);
        add(cbView);

        btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(340, 18, 120, 30);
        add(btnConsultar);

        model = new DefaultTableModel();
        tblResultado = new JTable(model);
        JScrollPane scroll = new JScrollPane(tblResultado);
        scroll.setBounds(20, 60, 710, 360);
        add(scroll);

        btnConsultar.addActionListener(e -> consultar());
    }

    private void consultar() {
        ViewDAO dao = new ViewDAO();
        try {
            ArrayList<Object[]> linhas = dao.consultar(cbView.getSelectedItem().toString());

            // monta as colunas de acordo com a view escolhida
            model.setRowCount(0);
            model.setColumnCount(0);
            for (String col : dao.getColunas()) {
                model.addColumn(col);
            }
            for (Object[] linha : linhas) {
                model.addRow(linha);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao consultar a view: " + ex.getMessage());
        }
    }
}
