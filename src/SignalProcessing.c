/* Exemples:
 *   compile and run: gcc ./git/etu_os/tst/SignalProcessing.c -o ./SignalProcessing && ./SignalProcessing
 *   send signal: kill -s SIGUSR1 %PID%
 */

#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <signal.h>
#include <string.h>

void numSignal (int sig){
	printf("%d\n", sig);
}

int main (){
	struct sigaction sa;
	memset (&sa, 0, sizeof(sa));
	sa.sa_handler = numSignal;
	sigaction(SIGUSR1, &sa, NULL);

	char key = 'r';
	while (key != 'q'){
		int pid = getpid();
		printf("%d\n", pid);
		key = getchar();
	}
	exit(0);
}
