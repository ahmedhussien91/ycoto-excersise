#! /bin/sh
case "$1" in
      start)
           echo "Starting readapp........."
           start-stop-daemon -S -n readapp -a /usr/bin/erro-app &
           ;;
     stop)
           echo "Stopping readapp........."
           start-stop-daemon -K -n erro-app
           ;;
     *)
           echo "Usage: $0 {start|stop}"
           exit 1
esac
exit 0