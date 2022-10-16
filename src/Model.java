import java.awt.*;
import java.util.*;

public class Model
{
	// 0 -> turno giocatore; 1 -> turno pc; 2 -> vittoria giocatore; 3 -> vittoria pc; 4 -> pareggio
	private int stato;
	private int[][] griglia;
	private ArrayList<Point> mossePC;
	private Point ultimaPosizione;
	private int difficolta;
	
	public Model()
	{
		stato = 0;
		griglia = new int[3][3];
		mossePC = new ArrayList<Point>();
		reset();
	}
	public Model(int difficolta)
	{
		this();
		this.difficolta = difficolta;
	}
	
	public int getStato() { return stato; }
	public int getValoreCasella(Point casella) { return griglia[casella.x][casella.y]; }
	
	public void reset()
	{
		stato = stato == 2 ? 1 : 0;
		for (int x = 0; x < 3; x++)
			for (int y = 0; y < 3; y++)
				griglia[x][y] = 0;
		mossePC.clear();
		for (int i = 0; i < 9; i++)
			mossePC.add(i, new Point(i % 3, i / 3));
		if (stato == 1)
			mossaPC();
	}
	public void mossaGiocatore(Point casella)
	{
		for (int i = 0; i < mossePC.size(); i++)
			if (mossePC.get(i).equals(casella))
				mossePC.remove(i);
		griglia[casella.x][casella.y] = 1;
		ultimaPosizione = casella;
		controllaGriglia();
	}
	public void mossaPC()
	{
		Random random;
		int base, mossa, mosseDisponibili;
		Point casella;
		
		mosseDisponibili = mossePC.size();
		random = new Random();
		//RANDOM
		mossa = -1;
		if (difficolta == 0)
		{
			mossa = random.nextInt(mosseDisponibili);
		}
		//CERCA DI VINCERE
		else if (difficolta == 1)
		{
			base = random.nextInt(3);
			if (base == 0)
			{}
			else if (base == 1)
			{}
			else
			{}
		}
		//CERCA DI VINCERE BLOCCANDO LE MIE MOSSE
		else if (difficolta == 2)
		{
			
		}
		//GOD MODE
		else if (difficolta == 3)
		{
			
		}
		casella = (Point)mossePC.get(mossa).clone();
		mossePC.remove(mossa);
		griglia[casella.x][casella.y] = -1;
		ultimaPosizione = casella;
		controllaGriglia();
	}
	public void controllaGriglia()
	{
		switch (controllaSomma())
		{
		case 3:
			stato = 2;
			break;
		case -3:
				stato = 3;
			break;
		default:
			stato = 1 - stato;
		}
		if (stato < 2 && mossePC.size() == 0)
			stato = 4;
	}
	public int controllaSomma()
	{
		int somma;
		
		somma = 0;
		for (int i = 0; i < 3; i++)
			somma += griglia[ultimaPosizione.x][i];
		if (somma == griglia[ultimaPosizione.x][ultimaPosizione.y] * 3)
			return somma;
		somma = 0;
		for (int i = 0; i < 3; i++)
			somma += griglia[i][ultimaPosizione.y];
		if (somma == griglia[ultimaPosizione.x][ultimaPosizione.y] * 3)
			return somma;
		if ((ultimaPosizione.x + ultimaPosizione.y) % 2 == 0)
		{
			if ((ultimaPosizione.x - ultimaPosizione.y) == 0 || ultimaPosizione.y == 1)
			{
				somma = 0;
				for (int i = 0; i < 3; i++)
					somma += griglia[i][i];
				if (somma == griglia[ultimaPosizione.x][ultimaPosizione.y] * 3)
					return somma;
			}
			if ((ultimaPosizione.x - ultimaPosizione.y) != 0 || ultimaPosizione.x == 1)
			{
				somma = 0;
				for (int i = 0; i < 3; i++)
					somma += griglia[i][2 - i];
				if (somma == griglia[ultimaPosizione.x][ultimaPosizione.y] * 3)
					return somma;
			}
		}
		
		return 0;
	}
}