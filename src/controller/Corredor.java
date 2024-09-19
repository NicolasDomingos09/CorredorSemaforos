package controller;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Corredor extends Thread {
	
	final int tamanho = 200;
	Semaphore semaforo;
	int[] pessoa = new int[2];

	public Corredor(Semaphore semaforo, int pessoa) {
		this.semaforo = semaforo;
		this.pessoa[1] = pessoa;
	}
	
	private void caminhar() {
		Random rand = new Random();
		
		while(pessoa[0] < tamanho) {
			if(pessoa[0] >= 194) {
				int comp = 200 - pessoa[0];
				pessoa[0] += comp;
				
			}else {
				pessoa[0] += rand.nextInt(4,6);
			}
			
			try {
				sleep(1000);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("Pessoa " + pessoa[1] + " andou um total de: " + pessoa[0] + " metros");
		}
		System.out.println("Pessoa " + pessoa[1] + " chegou ao fim do corredor");
		
	}

	private void porta() {
		Random rand = new Random();
		try {
			System.out.println("A pessoa " + pessoa[1] + " está atravesando a porta" );
			sleep(rand.nextInt(1000,2000));
			System.out.println("A pessoa " + pessoa[1] + " saiu pela porta");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		caminhar();
		
		try {
			semaforo.acquire();
			porta();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			semaforo.release();
		}
	}
}
