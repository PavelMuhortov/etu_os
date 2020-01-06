#include <stdlib.h>	//atoi()
#include <stdio.h>	//printf(), sprintf()
#include <unistd.h>	//getpid(), getppid(), fork(), execv(), sleep()

/**
  * @brief	Создает процессы-потомки, выводит информацию о процессе, после паузы завершается
  * @param	argv[1] - Количество создаваемых потомков
  * @param	argv[2] - Направление построения дерева в глубину/ширину (w/d)
  * @example	home-lab1 5 w
  */

void main (int argc, char **argv){
	if (argv[1] !=NULL && argv[2] !=NULL){
		int count = atoi(argv[1]);
		char direction = *argv[2];
		printf("ppid: %d, pid: %d, level: %s, direction: %c\n", getppid(), getpid(), argv[1], *argv[2]);
		// Создание процессов в глубину
		if (*argv[2] =='d' && count > 0){
			if (fork() == 0){
				sprintf(argv[1], "%d", count-1); //itoa
				char *args[]={argv[0],argv[1],argv[2],NULL};
				int childPid = execv(argv[0], args);
			}
		}
		// Создание процессов в ширину
		if (*argv[2] =='w' && count > 0){
			for (int i = 0; i < count; i++){
				if (fork() == 0){
					sprintf(argv[1], "%d", 0); //itoa
					char *args[]={argv[0],argv[1],argv[2],NULL};
					int childPid = execv(argv[0], args);
				}
			}
		}
		sleep(5+count);
		printf("ppid: %d, pid: %d exited\n", getppid(), getpid());
	}
	else {
		printf("ppid: %d, pid: %d\nFor forking need param\n", getppid(), getpid());
	}
}
