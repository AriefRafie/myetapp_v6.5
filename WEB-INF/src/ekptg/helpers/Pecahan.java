package ekptg.helpers;

import java.math.BigInteger;
import java.util.Vector;

public class Pecahan {

	private BigInteger pengangka;
	private BigInteger penyebut;

	public void setPengangka(BigInteger a) {
		this.pengangka = a;
	}

	public BigInteger getPengangka() {
		return pengangka;
	}

	public void setPenyebut(BigInteger b) {
		this.penyebut = b;
	}

	public BigInteger getPenyebut() {
		return penyebut;
	}

	public Pecahan(BigInteger a, BigInteger b) {
		if (b.compareTo(BigInteger.ZERO) == 0){
			b = BigInteger.ONE;
		}
		setPengangka(a);
		setPenyebut(b);
	}
	
	private BigInteger gcd (BigInteger a, BigInteger b) {
		  
		if (b.compareTo(BigInteger.ZERO) == 0)
			  return a;				  
		   return gcd(b,a.remainder(b));
	  }
	
	public Vector addToList(Vector list){
		
		if (list.size() == 0){
			list.add(this);
			
		} else {
			BigInteger pembawahBaru = BigInteger.ONE;
			BigInteger pembawahLama = BigInteger.ONE;
			
			boolean update = false;
			
			
			Pecahan pchn0 = (Pecahan) list.get(0);
				if (pchn0.getPenyebut() != this.getPenyebut()){
					update = true;
					pembawahBaru = this.getPenyebut();
					pembawahLama = pchn0.getPenyebut();
				}
				
			if (update){
				
				//update pecahan dlm list
				for (int i = 0; i < list.size(); i++){
					Pecahan pchn1 = (Pecahan) list.get(i);
					pchn1.setPengangka(pchn1.getPengangka().multiply(pembawahBaru));
					pchn1.setPenyebut(pchn1.getPenyebut().multiply(pembawahBaru));
					list.set(i, pchn1);
				}
				
				//update pecahan yang terbaru
				this.setPengangka(this.getPengangka().multiply(pembawahLama));
				this.setPenyebut(this.getPenyebut().multiply(pembawahLama));	
			}
			
			list.add(this);
			
			//get gcd
			BigInteger temp = this.getPenyebut();
			for (int j = 0; j < list.size(); j++) {
				Pecahan pchn2 = (Pecahan) list.get(j);
				if (pchn2.getPengangka().compareTo(BigInteger.ZERO) > 0){
					temp = gcd(temp,pchn2.getPengangka());
				}
			}

			//kecikkan
			if (temp.compareTo(BigInteger.ZERO) == 0){
				temp = BigInteger.ONE;
			}
			for (int k = 0; k < list.size(); k++) {
				Pecahan pchn3 = (Pecahan) list.get(k);
				pchn3.setPengangka(pchn3.getPengangka().divide(temp));
				pchn3.setPenyebut(pchn3.getPenyebut().divide(temp));
				list.set(k, pchn3);
			}
		}

		return list;
	}
}
