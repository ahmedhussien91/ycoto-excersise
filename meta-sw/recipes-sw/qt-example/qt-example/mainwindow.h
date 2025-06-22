#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>

QT_BEGIN_NAMESPACE
namespace Ui {
class MainWindow;
}
QT_END_NAMESPACE

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    MainWindow(QWidget *parent = nullptr);
    ~MainWindow();
private slots:
    void on_ConnectPB_clicked();
    void on_IncrementCountPB_clicked();
    void on_DecrementCountPB_clicked();
    void on_UnamePB_clicked();
    
private:
    Ui::MainWindow *ui;
    int count; // Variable to keep track of the count
};
#endif // MAINWINDOW_H
