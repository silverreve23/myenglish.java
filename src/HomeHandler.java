import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

class HomeHandler extends MouseAdapter {
    private Desktop desktop;
    private URI uri;

    public HomeHandler() {
        try {
            desktop = Desktop.getDesktop();
            uri = new URI("http://myenglish.tk/home");
        } catch (Exception exc) {
        }
    }

    public void mouseClicked(MouseEvent e) {
        try {
            desktop.browse(uri);
        } catch (Exception exc) {
        }
    }
}
