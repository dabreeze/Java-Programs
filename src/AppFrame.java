import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AppFrame extends JFrame
{

    private TitleBar title;
    private List list;
    private ButtonPannel btnPannel;
    private JButton addTask;
    private JButton clear;


    // constructor
    AppFrame()
    {
        this.setSize(400,700);
        this .setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        title = new TitleBar();
        list = new List();
        btnPannel = new ButtonPannel();
        this.add(title, BorderLayout.NORTH);
        this.add(list, BorderLayout.CENTER);
        this.add(btnPannel, BorderLayout.SOUTH);

        addTask = btnPannel.getAddTask();
        clear = btnPannel.getClear();

        addListeners();
    }

    public void addListeners(){
        addTask.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent mouseEvent)
            {
                Task task = new Task();
                list.add(task);
                list.updateNumbers();
                task.getDone().addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mousePressed(MouseEvent mouseEvent)
                    {
                        task.changeState();
                        revalidate();
                    }
                });
                revalidate();
            }
        });
        clear.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent event)
            {
                Task task = new Task();

                list.remove(task);
                list.updateNumbers();
                revalidate();
            }
        });

    }
}
