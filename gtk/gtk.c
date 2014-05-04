#include <gtk/gtk.h>

static gboolean delete_event(GtkWidget *wdgt, GdkEvent *ev, gpointer dt) {
    g_print("handler: delete_event\n");
    return FALSE;
}

static void destroy(GtkWidget *wdgt, gpointer dt) {
    g_print("handler: destroy #%02d\n", (int)dt);
    gtk_main_quit();
}

static void helloworld(GtkWidget *wdgt, gpointer dt) {
    g_print("button clicked...\n");
}

int main(int argc, char *argv[]) {

    GtkWidget *window, *button;

    /* initialize gtk... */
    gtk_init(&argc, &argv);

    /* instantiate widgets... */
    window = gtk_window_new(GTK_WINDOW_TOPLEVEL);
    button = gtk_button_new_with_label("Hello World");

    gtk_container_set_border_width(GTK_CONTAINER(window), 32);
    gtk_container_add(GTK_CONTAINER(window), button);

    /* connect signals... */
    g_signal_connect(window, "delete-event", G_CALLBACK(delete_event), NULL);
    g_signal_connect(window, "destroy", G_CALLBACK(destroy), (gpointer)1);
    g_signal_connect(button, "destroy", G_CALLBACK(destroy), (gpointer)2);
    g_signal_connect(button, "clicked", G_CALLBACK(helloworld), NULL);

    /* make widgets visible... */
    gtk_widget_show(button);
    gtk_widget_show(window);

    gtk_main();

    return 0;

}
