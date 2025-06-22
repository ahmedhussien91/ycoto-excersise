#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QMessageBox>
#include <QProcess>
#include <QDebug> // Add this for qDebug()

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
    , count(0) // initialize count
{
    ui->setupUi(this);
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::on_ConnectPB_clicked()
{
    qDebug() << "Connect button clicked";
    QMessageBox::information(this, "Qt on Linux", "You clicked the button!");
}

void MainWindow::on_IncrementCountPB_clicked()
{
    count++;
    qDebug() << "Increment button clicked, count is now:" << count;
    ui->outputLabel->setText(QString("Count: %1").arg(count));
}

void MainWindow::on_DecrementCountPB_clicked()
{
    count--;
    qDebug() << "Decrement button clicked, count is now:" << count;
    ui->outputLabel->setText(QString("Count: %1").arg(count));
}

void MainWindow::on_UnamePB_clicked()
{
    qDebug() << "Uname button clicked";
    QProcess process;
    process.start("uname", QStringList() << "-a");
    if (!process.waitForStarted()) {
        qDebug() << "Failed to start uname process";
        QMessageBox::warning(this, "Error", "Failed to start uname process.");
        return;
    }
    if (!process.waitForFinished()) {
        qDebug() << "Uname process did not finish properly";
        QMessageBox::warning(this, "Error", "Uname process did not finish properly.");
        return;
    }
    QString output = process.readAllStandardOutput();
    qDebug() << "Uname output:" << output.trimmed();
    ui->outputLabel->setText(output.trimmed());
}
