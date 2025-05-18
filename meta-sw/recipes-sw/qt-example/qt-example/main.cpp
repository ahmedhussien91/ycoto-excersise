#include "mainwindow.h"

#include <QApplication>
#include <QScreen>
#include <QGuiApplication>

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    MainWindow w;

    QScreen *screen = QGuiApplication::primaryScreen();
    QRect screenGeometry = screen->geometry();
    int height = screenGeometry.height();
    int width = screenGeometry.width();

    w.resize(width, height);
    w.show();
    return a.exec();
}
