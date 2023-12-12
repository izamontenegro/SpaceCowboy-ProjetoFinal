package modelagem;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;

public class TelaInicio extends JPanel implements ActionListener {
	private List<Estrelas> EstrelaBranca;
	private List<Estrelas> EstrelaRosa;
	private List<Estrelas> EstrelaAmarela;
	private List<Estrelas> EstrelaAzul;
	private Image seta;
	private Image fundoFaseX;
	private Color colorSingle = new Color(218, 165, 32);
	private Color colorMulti = new Color(218, 165, 32);
	private Color color = new Color(218, 165, 32);
	private int contador = 0;
	private Player player;
	File fontFile = new File("fontes\\BitBlox_Monospaced.otf");
	private int posicaoYSingle = 400;
	private int posicaoXSingle = 200;
	private int posicaoYMulti = 480;
	private int posicaoXMulti = 200;
	private int posicaoYTitulo = 200;
	private Timer timer;
	private Timer timer2;
	private Timer timer3;
	private Timer timer4;
	private Timer timer5;
	private boolean aumentando = true;
	private boolean aumentando2 = true;
	private boolean single = false;
	private boolean multi = false;
	private int xSeta = 130;
	private int ySeta = 360;
	private boolean jogo = false;
	private int abateInimigoRosa = 0;
	private int abateInimigoVerde = 0;
	private int abateInimigoLaranja = 0;
	private int pontuacaoTotal = 0;
	private int vidaPlayer = 6;
	private int vidaPlayer2 = 0;
	private int abateInimigoAzul = 0;
	private boolean emJogo = false;
	private Coracao coracao;
	private Clip clip;
	// private List<Asteroide> asteroides;
	// private List<InimigoAzul> inimigoAzul;
	private Horda1 horda1;
	private Horda2 horda2;
	private Horda3 horda3;
	private TelaQueda telaQueda;
	private Player2 player2;
	private Timer timer6;
	private boolean CriarPlayer2 = false;
	private Coracao coracao2;
	private boolean gameOver = false;

	public TelaInicio() {
		setFocusable(true);
		setDoubleBuffered(true);
		ImageIcon referencia = new ImageIcon("imagensTelaInicio//fundodTeste.png");
		fundoFaseX = referencia.getImage();
		ImageIcon setaFoto = new ImageIcon("imagensTelaInicio//seta.png");
		seta = setaFoto.getImage();
		addKeyListener(new TecladoAdapter());
		timer = new Timer(5, this);

		timer.start();
		coracao = new Coracao(0, -40);
		coracao.dadosImagem();
		coracao2 = new Coracao(500, -40);
		coracao2.dadosImagem();
		inicializaEstrelas();
		player = new Player();
		player.dadosImagem();
		player.setX(1100);
		player.setY(850);
		timer2 = new Timer(50, this::deslizamentoSingle);
		timer2.start();
		timer3 = new Timer(50, this::deslizamentoMulti);
		timer3.start();
		timer4 = new Timer(1, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				player.centraliza();

			}
		});

		timer4.start();
		timer5 = new Timer(30, this::tirarMenu);
		timer5.start();
		System.out.println(calculaPontuacao());
		adicionaSomFundo();

	}

	public int calculaPontuacao() {
		pontuacaoTotal = ((abateInimigoRosa * 200) + (abateInimigoAzul * 100) + (abateInimigoLaranja * 100)
				+ (abateInimigoVerde * 300));
		return pontuacaoTotal;
	}

	public Font carregarFonte(File caminhoFonte) {
		Font fontePontuacao = null;
		try {

			fontePontuacao = Font.createFont(Font.TRUETYPE_FONT, caminhoFonte).deriveFont(Font.BOLD, 100);

		} catch (FontFormatException | IOException e) {

		}

		return fontePontuacao;
	}

	public Font carregarFonte2(File caminhoFonte) {
		Font fontePontuacao = null;
		try {

			fontePontuacao = Font.createFont(Font.TRUETYPE_FONT, caminhoFonte).deriveFont(Font.BOLD, 35);

		} catch (FontFormatException | IOException e) {

		}

		return fontePontuacao;
	}

	public void adicionaSomFundo() {
		try {
			File audioFile = new File("sons//somfundo.wav");
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

			clip = AudioSystem.getClip();
			clip.open(audioStream);
		} catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
			e.printStackTrace();
		}

		playSound();

	}

	public void playSound() {
		if (clip != null) {
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}

	public void stopSound() {
		if (clip != null) {
			clip.stop();
		}
	}

	public void inicializaEstrelas() {
		int quantidade[] = new int[20];
		EstrelaBranca = new ArrayList<Estrelas>();
		EstrelaRosa = new ArrayList<Estrelas>();
		EstrelaAmarela = new ArrayList<Estrelas>();
		EstrelaAzul = new ArrayList<Estrelas>();

		for (int i = 0; i < (quantidade.length + 10); i++) {
			int x = (int) (Math.random() * -8000);
			int y = (int) (Math.random() * -500);
			EstrelaBranca.add(new Estrelas(x, y, 1));
		}

		for (int i = 0; i < quantidade.length; i++) {
			int x = (int) (Math.random() * -8000);
			int y = (int) (Math.random() * -4500);
			EstrelaAzul.add(new Estrelas(x, y, 3));
		}

		for (int j = 0; j < (quantidade.length - 10); j++) {
			int x = (int) (Math.random() * -8000);
			int y = (int) (Math.random() * -4500);
			EstrelaRosa.add(new Estrelas(x, y, 2));
		}

		for (int j = 0; j < quantidade.length; j++) {
			int x = (int) (Math.random() * -8000);
			int y = (int) (Math.random() * -4500);
			EstrelaAmarela.add(new Estrelas(x, y, 4));
		}
	}

	public void checarHorda() {
		Rectangle formaNave = player.getLimites();

		Rectangle formainimigoAzul;
		Rectangle formaTiro;
		Rectangle formaAtaqueInimigoRosa;
		Rectangle formaInimigoRosa;
		Rectangle formaMeteoro;
		Rectangle formaAsteroides;
		Rectangle formaInimigoVerde;
		Rectangle formaInimigoLaranja;
		Rectangle formaEscudo;
		Rectangle formaBonus;

		if (calculaPontuacao() < 1000) {
			// HORDA 1

			if (horda1 == null) {
				horda1 = new Horda1();
			}

			List<AtaquePlayer> ataques = player.getTiros();
			if (ataques != null) {
				for (int j = 0; j < ataques.size(); j++) {
					AtaquePlayer tempTiro = ataques.get(j);
					formaTiro = tempTiro.getLimites();
					for (int i = 0; i < horda1.getInimigosAzul().size(); i++) {
						InimigoAzul tempinimigoAzul = horda1.getInimigosAzul().get(i);
						formainimigoAzul = tempinimigoAzul.getLimites();
						if (formaTiro.intersects(formainimigoAzul)) {
							tempTiro.setVisible(false);
							tempinimigoAzul.setVisible(false);
							abateInimigoAzul += 1;

						}
					}
				}
			}
			if (horda1.getAsteroides() != null) {
				for (int k = 0; k < horda1.getAsteroides().size(); k++) {
					Asteroide tempAsteroide = horda1.getAsteroides().get(k);
					formaAsteroides = tempAsteroide.getLimites();
					if (formaNave.intersects(formaAsteroides)) {
						if (vidaPlayer <= 0) {
							player.setColisao(false);
						} else {
							player.setVisivel(false);
							tempAsteroide.setVisible(false);
							vidaPlayer -= 2;

						}

					}
				}

			}
			if (horda1.getInimigosAzul() != null) {
				for (int i = 0; i < horda1.getInimigosAzul().size(); i++) {
					InimigoAzul tempinimigoAzul = horda1.getInimigosAzul().get(i);
					formainimigoAzul = tempinimigoAzul.getLimites();
					if (formaNave.intersects(formainimigoAzul)) {
						if (vidaPlayer <= 0) {
							player.setColisao(false);
						} else {

							player.setVisivel(false);
							tempinimigoAzul.setVisible(false);
							vidaPlayer -= 1;
							player.setColisao(true);

						}

					}

				}
			}
			if (player2 != null) {

				Rectangle formaNave2 = player2.getLimites();
				Rectangle formaTiro2;
				List<AtaquePlayer> ataques2 = player2.getTiros();
				if (ataques2 != null) {
					for (int j = 0; j < ataques2.size(); j++) {
						AtaquePlayer tempTiro2 = ataques2.get(j);
						formaTiro2 = tempTiro2.getLimites();
						for (int i = 0; i < horda1.getInimigosAzul().size(); i++) {
							InimigoAzul tempinimigoAzul = horda1.getInimigosAzul().get(i);
							formainimigoAzul = tempinimigoAzul.getLimites();
							if (formaTiro2.intersects(formainimigoAzul)) {
								tempTiro2.setVisible(false);
								tempinimigoAzul.setVisible(false);
								abateInimigoAzul += 1;

							}
						}
					}
				}

				if (horda1.getInimigosAzul() != null) {
					for (int i = 0; i < horda1.getInimigosAzul().size(); i++) {
						InimigoAzul tempinimigoAzul = horda1.getInimigosAzul().get(i);
						formainimigoAzul = tempinimigoAzul.getLimites();
						if (formaNave2.intersects(formainimigoAzul)) {
							if (vidaPlayer2 <= 0) {
								player2.setColisao(false);
							} else {

								player2.setVisivel(false);
								tempinimigoAzul.setVisible(false);
								vidaPlayer2 -= 1;
								player2.setColisao(true);

							}

						}

					}

				}

				if (horda1.getAsteroides() != null) {
					for (int k = 0; k < horda1.getAsteroides().size(); k++) {
						Asteroide tempAsteroide = horda1.getAsteroides().get(k);
						formaAsteroides = tempAsteroide.getLimites();
						if (formaNave2.intersects(formaAsteroides)) {
							player2.setVisivel(false);
							if (vidaPlayer2 <= 0) {
								player2.setColisao(false);
							} else {
								tempAsteroide.setVisible(false);
								vidaPlayer2 -= 2;
								player2.setColisao(true);

							}

						}
					}

				}
			}

			horda1.actionPerformed(null);

		} else if (calculaPontuacao() >= 1000 && calculaPontuacao() < 2000) {
			// HORDA 2
			horda1 = null;

			if (horda2 == null) {
				horda2 = new Horda2();
			}

			if (horda2.getBonus() != null) {
				for (int i = 0; i < horda2.getBonus().size(); i++) {

					if (vidaPlayer > 0) {

						Bonus tempBonus = horda2.getBonus().get(i);
						formaBonus = tempBonus.getLimites();
						if (formaNave.intersects(formaBonus)) {
							tempBonus.setVisible(false);
							player.coletaBonus();
							if (tempBonus.getTipo() == 1) {
								player.setEscudo(true);
							} else if (tempBonus.getTipo() == 2 && this.vidaPlayer < 6) {
								this.vidaPlayer += 1;
							} else {
								player.setAtaqueEspecial(3);
							}

						}

					}
				}

			}

			if (player2 != null) {

				Rectangle formaTiro2;
				Rectangle formaNave2 = player2.getLimites();
				List<AtaquePlayer> ataques2 = player2.getTiros();
				if (ataques2 != null) {

					for (int j = 0; j < ataques2.size(); j++) {
						if (vidaPlayer2 > 0) {
							AtaquePlayer tempTiro2 = ataques2.get(j);
							formaTiro2 = tempTiro2.getLimites();
							for (int i = 0; i < horda2.getInimigosAzul().size(); i++) {
								InimigoAzul tempinimigoAzul = horda2.getInimigosAzul().get(i);
								formainimigoAzul = tempinimigoAzul.getLimites();
								if (formaTiro2.intersects(formainimigoAzul)) {
									tempinimigoAzul.setVisible(false);
									tempTiro2.setVisible(false);
									abateInimigoAzul += 1;

								}
							}
							for (int i = 0; i < horda2.getInimigosRosa().size(); i++) {
								InimigoRosa tempinimigoRosa = horda2.getInimigosRosa().get(i);
								formaInimigoRosa = tempinimigoRosa.getLimites();
								if (formaTiro2.intersects(formaInimigoRosa)) {
									tempinimigoRosa.setVisible(false);
									tempTiro2.setVisible(false);
									abateInimigoRosa += 1;
								}
							}

						}
					}

				}
				if (horda2.getBonus() != null) {
					for (int i = 0; i < horda2.getBonus().size(); i++) {
						if (vidaPlayer2 > 0) {

							Bonus tempBonus = horda2.getBonus().get(i);
							formaBonus = tempBonus.getLimites();
							if (formaNave2.intersects(formaBonus)) {
								tempBonus.setVisible(false);
								player2.coletaBonus();
								if (tempBonus.getTipo() == 1) {
									player2.setEscudo(true);
								} else if (tempBonus.getTipo() == 2 && this.vidaPlayer2 < 6) {
									this.vidaPlayer2 += 1;
								} else {
									player2.setAtaqueEspecial(3);
								}

							}

						}

					}
				}
				if (horda2.getAsteroides() != null) {
					for (int k = 0; k < horda2.getAsteroides().size(); k++) {
						Asteroide tempAsteroide = horda2.getAsteroides().get(k);
						formaAsteroides = tempAsteroide.getLimites();
						if (formaNave2.intersects(formaAsteroides)) {
							player2.setVisivel(false);
							if (vidaPlayer2 <= 0) {
								player2.setColisao(false);

							} else {
								tempAsteroide.setVisible(false);
								vidaPlayer2 -= 2;
								player2.setColisao(true);

							}

						}
					}

				}
				if (horda2.getInimigosAzul() != null) {

					for (int i = 0; i < horda2.getInimigosAzul().size(); i++) {
						InimigoAzul tempinimigoAzul = horda2.getInimigosAzul().get(i);
						formainimigoAzul = tempinimigoAzul.getLimites();
						if (formaNave2.intersects(formainimigoAzul)) {
							if (vidaPlayer2 <= 0) {
								player2.setColisao(false);

							} else {

								player2.setVisivel(false);
								tempinimigoAzul.setVisible(false);
								vidaPlayer2 -= 1;
								player2.setColisao(true);

							}

						}

					}
				}
				if (horda2.getInimigosRosa() != null) {
					for (int k = 0; k < horda2.getInimigosRosa().size(); k++) {
						InimigoRosa tempinimigoAzul = horda2.getInimigosRosa().get(k);
						formainimigoAzul = tempinimigoAzul.getLimites();
						if (formaNave2.intersects(formainimigoAzul)) {
							if (vidaPlayer2 <= 0) {
								player2.setColisao(false);

							} else {

								player2.setVisivel(false);
								tempinimigoAzul.setVisible(false);
								vidaPlayer2 -= 1;
								player2.setColisao(true);

							}

						}
					}
				}
				if (horda2.getInimigosRosa() != null) {
					for (int k = 0; k < horda2.getInimigosRosa().size(); k++) {
						List<AtaqueInimigo> ataques = horda2.getInimigosRosa().get(k).getAtaques();

						for (int x = 0; x < ataques.size(); x++) {
							AtaqueInimigo tempataquerosa = ataques.get(x);
							formaAtaqueInimigoRosa = tempataquerosa.getLimites();
							if (formaNave2.intersects(formaAtaqueInimigoRosa)) {
								if (vidaPlayer2 <= 0) {
									player2.setColisao(false);

								} else {

									player2.setVisivel(false);
									tempataquerosa.setVisible(false);
									vidaPlayer2 -= 1;
									player2.setColisao(true);

								}

							}

						}
					}
				}
			}
			if (horda2.getInimigosRosa() != null) {

				for (int k = 0; k < horda2.getInimigosRosa().size(); k++) {
					List<AtaqueInimigo> ataques = horda2.getInimigosRosa().get(k).getAtaques();

					for (int x = 0; x < ataques.size(); x++) {
						AtaqueInimigo tempataquerosa = ataques.get(x);
						formaAtaqueInimigoRosa = tempataquerosa.getLimites();
						if (formaNave.intersects(formaAtaqueInimigoRosa)) {

							if (vidaPlayer <= 0) {
								player.setColisao(false);

							} else {

								player.setVisivel(false);
								tempataquerosa.setVisible(false);
								vidaPlayer -= 1;
								player.setColisao(true);

							}

						}

					}
				}

			}

			List<AtaquePlayer> ataques = player.getTiros();
			if (ataques != null) {

				for (int j = 0; j < ataques.size(); j++) {

					if (vidaPlayer > 0) {

						AtaquePlayer tempTiro = ataques.get(j);
						formaTiro = tempTiro.getLimites();

						for (int i = 0; i < horda2.getInimigosAzul().size(); i++) {
							InimigoAzul tempinimigoAzul = horda2.getInimigosAzul().get(i);
							formainimigoAzul = tempinimigoAzul.getLimites();
							if (formaTiro.intersects(formainimigoAzul)) {
								tempTiro.setVisible(false);
								tempinimigoAzul.setVisible(false);
								abateInimigoAzul += 1;

							}
						}
						for (int i = 0; i < horda2.getInimigosRosa().size(); i++) {
							InimigoRosa tempinimigoRosa = horda2.getInimigosRosa().get(i);
							formaInimigoRosa = tempinimigoRosa.getLimites();
							if (formaTiro.intersects(formaInimigoRosa)) {
								tempinimigoRosa.setVisible(false);
								tempTiro.setVisible(false);
								abateInimigoRosa += 1;
							}
						}

					}

				}

			}
			if (horda2.getInimigosAzul() != null) {
				for (int k = 0; k < horda2.getInimigosAzul().size(); k++) {
					InimigoAzul tempinimigoAzul = horda2.getInimigosAzul().get(k);
					formainimigoAzul = tempinimigoAzul.getLimites();
					if (formaNave.intersects(formainimigoAzul)) {
						if (vidaPlayer <= 0) {
							player.setColisao(false);
						} else {

							player.setVisivel(false);
							tempinimigoAzul.setVisible(false);
							vidaPlayer -= 1;
							player.setColisao(true);

						}

					}

				}

			}
			if (horda2.getInimigosRosa() != null) {
				for (int k = 0; k < horda2.getInimigosRosa().size(); k++) {
					InimigoRosa tempinimigoAzul = horda2.getInimigosRosa().get(k);
					formainimigoAzul = tempinimigoAzul.getLimites();
					if (formaNave.intersects(formainimigoAzul)) {
						if (vidaPlayer <= 0) {
							player.setColisao(false);
						} else {

							player.setVisivel(false);
							tempinimigoAzul.setVisible(false);
							vidaPlayer -= 1;
							player.setColisao(true);

						}

					}

				}
			}
			if (horda2.getAsteroides() != null) {
				for (int k = 0; k < horda2.getAsteroides().size(); k++) {
					Asteroide tempAsteroide = horda2.getAsteroides().get(k);
					formaAsteroides = tempAsteroide.getLimites();
					if (formaNave.intersects(formaAsteroides)) {
						player.setVisivel(false);
						if (vidaPlayer <= 0) {
							player.setColisao(false);
						} else {
							tempAsteroide.setVisible(false);
							vidaPlayer -= 2;
							player.setColisao(true);

						}

					}
				}
			}

			horda2.actionPerformed(null);
		} else if (calculaPontuacao() >= 2000) {
			horda2 = null;
			if (horda3 == null) {
				horda3 = new Horda3();
			}
			if (player2 != null) {
				if (horda3.getMeteoros() != null) {
					for (int i = 0; i < horda3.getMeteoros().size(); i++) {
						Meteoro tempinimigoAzul = horda3.getMeteoros().get(i);
						formainimigoAzul = tempinimigoAzul.getLimites();
						if (formaNave.intersects(formainimigoAzul)) {
							if (vidaPlayer2 <= 0) {
								player2.setColisao(false);
							} else {

								player.setVisivel(false);
								tempinimigoAzul.setVisible(false);
								vidaPlayer -= 1;
								player.setColisao(true);

							}

						}

					}
				}
				Rectangle formaTiro2;
				Rectangle formaNave2 = player2.getLimites();
				List<AtaquePlayer> ataques2 = player2.getTiros();
				if (ataques2 != null) {
					for (int j = 0; j < ataques2.size(); j++) {
						if (vidaPlayer2 > 0) {
							AtaquePlayer tempTiro2 = ataques2.get(j);
							formaTiro2 = tempTiro2.getLimites();
							for (int i = 0; i < horda3.getInimigosAzul().size(); i++) {
								InimigoAzul tempinimigoAzul = horda3.getInimigosAzul().get(i);
								formainimigoAzul = tempinimigoAzul.getLimites();
								if (formaTiro2.intersects(formainimigoAzul)) {
									tempTiro2.setVisible(false);
									tempinimigoAzul.setVisible(false);
									abateInimigoAzul += 1;

								}
							}
							for (int i = 0; i < horda3.getInimigosRosa().size(); i++) {
								InimigoRosa tempinimigoRosa = horda3.getInimigosRosa().get(i);
								formaInimigoRosa = tempinimigoRosa.getLimites();
								if (formaTiro2.intersects(formaInimigoRosa)) {
									tempinimigoRosa.setVisible(false);
									tempTiro2.setVisible(false);
									
									abateInimigoRosa += 1;
								}
							}
							for (int i = 0; i < horda3.getInimigosVerde().size(); i++) {
								InimigoVerde tempinimigoRosa = horda3.getInimigosVerde().get(i);
								formaInimigoRosa = tempinimigoRosa.getLimites();
								if (formaTiro2.intersects(formaInimigoRosa)) {
									tempinimigoRosa.setVisible(false);
									tempTiro2.setVisible(false);
									abateInimigoRosa += 1;
								}
							}
							for (int i = 0; i < horda3.getInimigosLaranja().size(); i++) {
								InimigoLaranja tempinimigoRosa = horda3.getInimigosLaranja().get(i);
								formaInimigoRosa = tempinimigoRosa.getLimites();
								if (formaTiro2.intersects(formaInimigoRosa)) {
									tempinimigoRosa.setVisible(false);
									tempTiro2.setVisible(false);
									abateInimigoRosa += 1;
								}
							}

						}
					}

				}
				if (horda3.getBonus() != null) {
					if (vidaPlayer2 > 0) {

						for (int i = 0; i < horda3.getBonus().size(); i++) {
							Bonus tempBonus = horda3.getBonus().get(i);
							formaBonus = tempBonus.getLimites();
							if (formaNave2.intersects(formaBonus)) {
								tempBonus.setVisible(false);
								player2.coletaBonus();
								if (tempBonus.getTipo() == 1) {
									player2.setEscudo(true);
								} else if (tempBonus.getTipo() == 2 && this.vidaPlayer2 < 6) {
									this.vidaPlayer2 += 1;
								} else {
									player2.setAtaqueEspecial(3);
								}

							}
						}

					}
				}
				if (horda3.getAsteroides() != null) {
					for (int k = 0; k < horda3.getAsteroides().size(); k++) {
						Asteroide tempAsteroide = horda3.getAsteroides().get(k);
						formaAsteroides = tempAsteroide.getLimites();
						if (formaNave2.intersects(formaAsteroides)) {
							player2.setVisivel(false);
							if (vidaPlayer2 <= 0) {
								player2.setColisao(false);

							} else {
								tempAsteroide.setVisible(false);
								vidaPlayer2 -= 2;
								player2.setColisao(true);

							}

						}
					}

				}
				if (horda3.getInimigosRosa() != null) {
					for (int k = 0; k < horda3.getInimigosRosa().size(); k++) {
						List<AtaqueInimigo> ataquesRosa = horda3.getInimigosRosa().get(k).getAtaques();

						for (int x = 0; x < ataquesRosa.size(); x++) {
							AtaqueInimigo tempataquerosa = ataquesRosa.get(x);
							formaAtaqueInimigoRosa = tempataquerosa.getLimites();
							if (formaAtaqueInimigoRosa.intersects(formaNave2)) {
								tempataquerosa.setVisible(false);
								if (vidaPlayer2 <= 0) {
									player2.setColisao(false);

								} else {

									player2.setVisivel(false);
									tempataquerosa.setVisible(false);
									vidaPlayer2 -= 1;
									player2.setColisao(true);

								}

							}

						}
					}

				}
				if (horda3.getInimigosVerde() != null) {
					for (int k = 0; k < horda3.getInimigosVerde().size(); k++) {
						List<AtaqueInimigo> ataquesR = horda3.getInimigosVerde().get(k).getAtaques();

						for (int x = 0; x < ataquesR.size(); x++) {
							AtaqueInimigo tempataquerosa = ataquesR.get(x);
							formaAtaqueInimigoRosa = tempataquerosa.getLimites();
							if (formaAtaqueInimigoRosa.intersects(formaNave2)) {
								tempataquerosa.setVisible(false);
								if (vidaPlayer2 <= 0) {
									player2.setColisao(false);

								} else {

									player2.setVisivel(false);
									tempataquerosa.setVisible(false);
									vidaPlayer2 -= 1;
									player2.setColisao(true);

								}

							}

						}
					}

				}
				if (horda3.getInimigosLaranja() != null) {
					for (int k = 0; k < horda3.getInimigosLaranja().size(); k++) {
						List<AtaqueInimigo> ataquesLaranjaD3 = horda3.getInimigosLaranja().get(k).getAtaquesD();

						for (int x = 0; x < ataquesLaranjaD3.size(); x++) {
							AtaqueInimigo tempataquerosa = ataquesLaranjaD3.get(x);
							formaAtaqueInimigoRosa = tempataquerosa.getLimites();
							if (formaAtaqueInimigoRosa.intersects(formaNave2)) {
								tempataquerosa.setVisible(false);
								if (vidaPlayer2 <= 0) {
									player2.setColisao(false);

								} else {

									player2.setVisivel(false);
									tempataquerosa.setVisible(false);
									vidaPlayer2 -= 1;
									player2.setColisao(true);

								}

							}

						}
					}

				}
				if (horda3.getInimigosLaranja() != null) {
					for (int k = 0; k < horda3.getInimigosLaranja().size(); k++) {
						List<AtaqueInimigo> ataquesLaranjaE3 = horda3.getInimigosLaranja().get(k).getAtaquesE();

						for (int x = 0; x < ataquesLaranjaE3.size(); x++) {
							AtaqueInimigo tempataquerosa = ataquesLaranjaE3.get(x);
							formaAtaqueInimigoRosa = tempataquerosa.getLimites();
							if (formaAtaqueInimigoRosa.intersects(formaNave2)) {
								tempataquerosa.setVisible(false);
								if (vidaPlayer2 <= 0) {
									player2.setColisao(false);

								} else {

									player2.setVisivel(false);
									tempataquerosa.setVisible(false);
									vidaPlayer2 -= 1;
									player2.setColisao(true);

								}

							}

						}
					}

				}
				if (horda3.getInimigosAzul() != null) {
					for (int i = 0; i < horda3.getInimigosAzul().size(); i++) {
						InimigoAzul tempinimigoAzul = horda3.getInimigosAzul().get(i);
						formainimigoAzul = tempinimigoAzul.getLimites();
						if (formaNave2.intersects(formainimigoAzul)) {
							if (vidaPlayer2 <= 0) {
								player2.setColisao(false);

							} else {

								player2.setVisivel(false);
								tempinimigoAzul.setVisible(false);
								vidaPlayer2 -= 1;
								player2.setColisao(true);

							}

						}

					}

				}
				if (horda3.getMeteoros() != null) {
					for (int i = 0; i < horda3.getMeteoros().size(); i++) {
						Meteoro tempinimigoAzul = horda3.getMeteoros().get(i);
						formainimigoAzul = tempinimigoAzul.getLimites();
						if (formaNave2.intersects(formainimigoAzul)) {
							if (vidaPlayer2 <= 0) {
								player2.setColisao(false);

							} else {

								player2.setVisivel(false);
								tempinimigoAzul.setVisible(false);
								vidaPlayer2 -= 1;
								player2.setColisao(true);

							}

						}

					}

				}
				if (horda3.getInimigosRosa() != null) {
					for (int k = 0; k < horda3.getInimigosRosa().size(); k++) {
						InimigoRosa tempinimigoAzul = horda3.getInimigosRosa().get(k);
						formainimigoAzul = tempinimigoAzul.getLimites();
						if (formaNave2.intersects(formainimigoAzul)) {
							if (vidaPlayer2 <= 0) {
								player2.setColisao(false);

							} else {

								player2.setVisivel(false);
								tempinimigoAzul.setVisible(false);
								vidaPlayer2 -= 1;
								player2.setColisao(true);

							}

						}

					}

				}
				if (horda3.getInimigosVerde() != null) {
					for (int k = 0; k < horda3.getInimigosVerde().size(); k++) {
						InimigoVerde tempinimigoAzul = horda3.getInimigosVerde().get(k);
						formainimigoAzul = tempinimigoAzul.getLimites();
						if (formaNave2.intersects(formainimigoAzul)) {
							if (vidaPlayer2 <= 0) {
								player2.setColisao(false);

							} else {

								player2.setVisivel(false);
								tempinimigoAzul.setVisible(false);
								vidaPlayer2 -= 1;
								player2.setColisao(true);

							}

						}

					}

				}
				if (horda3.getInimigosLaranja() != null) {
					for (int k = 0; k < horda3.getInimigosLaranja().size(); k++) {
						InimigoLaranja tempinimigoAzul = horda3.getInimigosLaranja().get(k);
						formainimigoAzul = tempinimigoAzul.getLimites();
						if (formaNave2.intersects(formainimigoAzul)) {
							if (vidaPlayer2 <= 0) {
								player2.setColisao(false);

							} else {

								player2.setVisivel(false);
								tempinimigoAzul.setVisible(false);
								vidaPlayer2 -= 1;
								player2.setColisao(true);

							}

						}

					}
				}
			}

			List<AtaquePlayer> ataques = player.getTiros();
			if (ataques != null) {
				for (int j = 0; j < ataques.size(); j++) {
					if (vidaPlayer > 0) {

						AtaquePlayer tempTiro = ataques.get(j);
						formaTiro = tempTiro.getLimites();
						for (int i = 0; i < horda3.getInimigosAzul().size(); i++) {
							InimigoAzul tempinimigoAzul = horda3.getInimigosAzul().get(i);
							formainimigoAzul = tempinimigoAzul.getLimites();
							if (formaTiro.intersects(formainimigoAzul)) {
								tempTiro.setVisible(false);

								abateInimigoAzul += 1;

							}
						}
						for (int i = 0; i < horda3.getInimigosRosa().size(); i++) {
							InimigoRosa tempinimigoRosa = horda3.getInimigosRosa().get(i);
							formaInimigoRosa = tempinimigoRosa.getLimites();
							if (formaTiro.intersects(formaInimigoRosa)) {
								tempinimigoRosa.setVisible(false);
								tempTiro.setVisible(false);
								abateInimigoRosa += 1;
							}
						}
						for (int i = 0; i < horda3.getInimigosVerde().size(); i++) {
							InimigoVerde tempinimigoRosa = horda3.getInimigosVerde().get(i);
							formaInimigoRosa = tempinimigoRosa.getLimites();
							if (formaTiro.intersects(formaInimigoRosa)) {
								tempinimigoRosa.setVisible(false);
								tempTiro.setVisible(false);
								abateInimigoRosa += 1;
							}
						}
						for (int i = 0; i < horda3.getInimigosLaranja().size(); i++) {
							InimigoLaranja tempinimigoRosa = horda3.getInimigosLaranja().get(i);
							formaInimigoRosa = tempinimigoRosa.getLimites();
							if (formaTiro.intersects(formaInimigoRosa)) {
								tempinimigoRosa.setVisible(false);
								tempTiro.setVisible(false);
								abateInimigoRosa += 1;
							}
						}

					}
				}

			}
			if (horda3.getBonus() != null) {
				for (int i = 0; i < horda3.getBonus().size(); i++) {
					if (vidaPlayer > 0) {

						Bonus tempBonus = horda3.getBonus().get(i);
						formaBonus = tempBonus.getLimites();
						if (formaNave.intersects(formaBonus)) {
							tempBonus.setVisible(false);
							player.coletaBonus();
							if (tempBonus.getTipo() == 1) {
								player.setEscudo(true);
							} else if (tempBonus.getTipo() == 2 && this.vidaPlayer < 6) {
								this.vidaPlayer += 1;
							} else {
								player.setAtaqueEspecial(3);
							}

						}

					}
				}

			}

			if (horda3.getInimigosRosa() != null) {
				for (int k = 0; k < horda3.getInimigosRosa().size(); k++) {
					List<AtaqueInimigo> ataquesRosa = horda3.getInimigosRosa().get(k).getAtaques();

					for (int x = 0; x < ataquesRosa.size(); x++) {
						AtaqueInimigo tempataquerosa = ataquesRosa.get(x);
						formaAtaqueInimigoRosa = tempataquerosa.getLimites();
						if (formaAtaqueInimigoRosa.intersects(formaNave)) {

							if (vidaPlayer <= 0) {
								player.setColisao(false);

							} else {

								player.setVisivel(false);
								tempataquerosa.setVisible(false);
								vidaPlayer -= 1;
								player.setColisao(true);

							}

						}

					}
				}
			}

			if (horda3.getInimigosVerde() != null) {
				for (int k = 0; k < horda3.getInimigosVerde().size(); k++) {
					List<AtaqueInimigo> ataquesR = horda3.getInimigosVerde().get(k).getAtaques();

					for (int x = 0; x < ataquesR.size(); x++) {
						AtaqueInimigo tempataquerosa = ataquesR.get(x);
						formaAtaqueInimigoRosa = tempataquerosa.getLimites();
						if (formaAtaqueInimigoRosa.intersects(formaNave)) {

							if (vidaPlayer <= 0) {
								player.setColisao(false);

							} else {

								player.setVisivel(false);
								tempataquerosa.setVisible(false);
								vidaPlayer -= 1;
								player.setColisao(true);

							}

						}

					}
				}
			}
			if (horda3.getInimigosLaranja() != null) {
				for (int k = 0; k < horda3.getInimigosLaranja().size(); k++) {
					List<AtaqueInimigo> ataquesLaranjaD3 = horda3.getInimigosLaranja().get(k).getAtaquesD();

					for (int x = 0; x < ataquesLaranjaD3.size(); x++) {
						AtaqueInimigo tempataquerosa = ataquesLaranjaD3.get(x);
						formaAtaqueInimigoRosa = tempataquerosa.getLimites();
						if (formaAtaqueInimigoRosa.intersects(formaNave)) {

							if (vidaPlayer <= 0) {
								player.setColisao(false);

							} else {

								player.setVisivel(false);
								tempataquerosa.setVisible(false);
								vidaPlayer -= 1;
								player.setColisao(true);

							}

						}

					}
				}

			}
			if (horda3.getInimigosLaranja() != null) {
				for (int k = 0; k < horda3.getInimigosLaranja().size(); k++) {
					List<AtaqueInimigo> ataquesLaranjaE3 = horda3.getInimigosLaranja().get(k).getAtaquesE();

					for (int x = 0; x < ataquesLaranjaE3.size(); x++) {
						AtaqueInimigo tempataquerosa = ataquesLaranjaE3.get(x);
						formaAtaqueInimigoRosa = tempataquerosa.getLimites();
						if (formaAtaqueInimigoRosa.intersects(formaNave)) {

							if (vidaPlayer <= 0) {
								player.setColisao(false);

							} else {

								player.setVisivel(false);
								tempataquerosa.setVisible(false);
								vidaPlayer -= 1;
								player.setColisao(true);

							}

						}

					}
				}
			}
			if (horda3.getAsteroides() != null) {
				for (int k = 0; k < horda3.getAsteroides().size(); k++) {
					Asteroide tempAsteroide = horda3.getAsteroides().get(k);
					formaAsteroides = tempAsteroide.getLimites();
					if (formaNave.intersects(formaAsteroides)) {
						player.setVisivel(false);
						if (vidaPlayer <= 0) {
							player.setColisao(false);

						} else {
							tempAsteroide.setVisible(false);
							vidaPlayer -= 2;
							player.setColisao(true);

						}

					}
				}
			}

			if (horda3.getInimigosAzul() != null) {
				for (int i = 0; i < horda3.getInimigosAzul().size(); i++) {
					InimigoAzul tempinimigoAzul = horda3.getInimigosAzul().get(i);
					formainimigoAzul = tempinimigoAzul.getLimites();
					if (formaNave.intersects(formainimigoAzul)) {
						if (vidaPlayer <= 0) {
							player.setColisao(false);

						} else {

							player.setVisivel(false);
							tempinimigoAzul.setVisible(false);
							vidaPlayer -= 1;
							player.setColisao(true);

						}

					}

				}
			}
			if (horda3.getInimigosRosa() != null) {
				for (int k = 0; k < horda3.getInimigosRosa().size(); k++) {
					InimigoRosa tempinimigoAzul = horda3.getInimigosRosa().get(k);
					formainimigoAzul = tempinimigoAzul.getLimites();
					if (formaNave.intersects(formainimigoAzul)) {
						if (vidaPlayer <= 0) {
							player.setColisao(false);

						} else {

							player.setVisivel(false);
							tempinimigoAzul.setVisible(false);
							vidaPlayer -= 1;
							player.setColisao(true);

						}

					}

				}
			}
			if (horda3.getInimigosVerde() != null) {

				for (int k = 0; k < horda3.getInimigosVerde().size(); k++) {
					InimigoVerde tempinimigoAzul = horda3.getInimigosVerde().get(k);
					formainimigoAzul = tempinimigoAzul.getLimites();
					if (formaNave.intersects(formainimigoAzul)) {
						if (vidaPlayer <= 0) {
							player.setColisao(false);

						} else {

							player.setVisivel(false);
							tempinimigoAzul.setVisible(false);
							vidaPlayer -= 1;
							player.setColisao(true);

						}

					}

				}
			}
			if (horda3.getInimigosLaranja() != null) {
				for (int k = 0; k < horda3.getInimigosLaranja().size(); k++) {
					InimigoLaranja tempinimigoAzul = horda3.getInimigosLaranja().get(k);
					formainimigoAzul = tempinimigoAzul.getLimites();
					if (formaNave.intersects(formainimigoAzul)) {
						if (vidaPlayer <= 0) {
							player.setColisao(false);

						} else {

							player.setVisivel(false);
							tempinimigoAzul.setVisible(false);
							vidaPlayer -= 1;
							player.setColisao(true);

						}

					}

				}
			}

			horda3.actionPerformed(null);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(player!=null) {
			if (player.getComecaJogo()) {
				player.movimenta();
			}
			
		}
		if (player2 != null) {
			if (player2 != null) {

				List<AtaquePlayer> tiros2 = player2.getTiros();
				for (int i = 0; i < tiros2.size(); i++) {
					AtaquePlayer m = tiros2.get(i);
					if (m.isVisible()) {
						m.movimenta();

					} else {
						tiros2.remove(i);
					}
				}

			}
			player2.movimenta();
			player2.centraliza();
		}

		if (emJogo) {
			checarHorda();
		}
		if(player!=null) {
			List<AtaquePlayer> tiros = player.getTiros();
			if(tiros!=null) {
				
				if (!player.getComecaJogo()) {
					contador++;
					if (!jogo) {
						player.movimentaInicio();
					}
					
					if (contador % 50 == 0) {
						player.tiroSimples();
					}
				}
				for (int i = 0; i < tiros.size(); i++) {
					AtaquePlayer m = tiros.get(i);
					if (m.isVisible()) {
						m.movimenta();
						
					} else {
						tiros.remove(i);
					}
				}
			}
			
			
		}


		for (int p = 0; p < EstrelaBranca.size(); p++) {
			Estrelas on = EstrelaBranca.get(p);
			if (on.isVisible()) {
				on.movimenta();
			} else
				EstrelaBranca.remove(p);
		}

		for (int p = 0; p < EstrelaRosa.size(); p++) {
			Estrelas on = EstrelaRosa.get(p);
			if (on.isVisible()) {
				on.movimenta();
			} else
				EstrelaRosa.remove(p);
		}

		for (int p = 0; p < EstrelaAmarela.size(); p++) {
			Estrelas on = EstrelaAmarela.get(p);
			if (on.isVisible()) {
				on.movimenta();
			} else
				EstrelaAmarela.remove(p);
		}

		for (int p = 0; p < EstrelaAzul.size(); p++) {
			Estrelas on = EstrelaAzul.get(p);
			if (on.isVisible()) {
				on.movimenta();
			} else
				EstrelaAzul.remove(p);
		}
		verificarDerrota();
	
		repaint();
	}

	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundoFaseX, 0, 0, null);
		if(player!=null) {
			if(vidaPlayer>0) {
				List<AtaquePlayer> tiros = player.getTiros();
				graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);
				if(tiros!=null) {
					for (int i = 0; i < tiros.size(); i++) {
						AtaquePlayer m = tiros.get(i);
						m.dadosImagem();
						
						graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
					}
					
				}
				
					
				
			}else {
				graficos.drawImage(player.getImagem(), player.getX(), player.getY(),0,0, this);
			}
			
			
		}
		
		
		
		graficos.setFont(carregarFonte(fontFile));
		graficos.setColor(color);
		graficos.drawString("Space ", 220, posicaoYTitulo);
		graficos.drawString("COWBOY ", 200, posicaoYTitulo+100);
		graficos.setFont(carregarFonte2(fontFile));
		graficos.setColor(colorSingle);
		graficos.drawString("single player",posicaoXSingle,posicaoYSingle);
		graficos.setFont(carregarFonte2(fontFile));
		graficos.setColor(colorMulti);
		graficos.drawString("multi player", posicaoXMulti,posicaoYMulti);
		
		if(!emJogo) {
			for (int p = 0; p < EstrelaBranca.size(); p++) {
				Estrelas q = EstrelaBranca.get(p);
				q.dadosImagem();
				graficos.drawImage(q.getImagem(), q.getX(), q.getY(), this);
			}
			
			for (int p = 0; p < EstrelaRosa.size(); p++) {
				Estrelas q = EstrelaRosa.get(p);
				q.dadosImagem();
				graficos.drawImage(q.getImagem(), q.getX(), q.getY(), this);
			}
			
			for (int p = 0; p < EstrelaAmarela.size(); p++) {
				Estrelas q = EstrelaAmarela.get(p);
				q.dadosImagem();
				graficos.drawImage(q.getImagem(), q.getX(), q.getY(), this);
			}
			
			for (int p = 0; p < EstrelaAzul.size(); p++) {
				Estrelas q = EstrelaAzul.get(p);
				q.dadosImagem();
				graficos.drawImage(q.getImagem(), q.getX(), q.getY(), this);
			}
			
			if(!player.getComecaJogo()) {			
				graficos.drawImage(seta, xSeta, ySeta,64,64, null);
			}
		if(vidaPlayer<=0 && vidaPlayer2<=0) {
			telaQueda.paint(graficos);
		}
				
			
			
			
		}else {
			if(player2!=null) {
				if(vidaPlayer2>0) {
					graficos.drawImage(player2.getImagem(), player2.getX(), player2.getY(), this);
					
				}else {
					graficos.drawImage(player2.getImagem(), player2.getX(), player2.getY(), 0,0,this);
				}
				if(vidaPlayer2<=0) {
					graficos.drawImage(coracao2.getImagem(), coracao2.getX(), coracao2.getY(), this);
				}else if(vidaPlayer2>0) {
					graficos.drawImage(coracao2.getImagem(vidaPlayer2), coracao2.getX(), coracao2.getY(), this);
					
				}
				if (player2.getTiros()!=null) {
					if(vidaPlayer2>0) {
						
						List<AtaquePlayer> tiros2 = player2.getTiros();
						for (int i = 0; i < tiros2.size(); i++) {
							AtaquePlayer m = tiros2.get(i);
							m.dadosImagem();
							
							graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
						}
					}
					
					
				}
				
					
				
			}
			if(vidaPlayer<=0) {
				graficos.drawImage(coracao.getImagem(), coracao.getX(), coracao.getY(), this);
			}else if (vidaPlayer>0){
				graficos.drawImage(coracao.getImagem(vidaPlayer), coracao.getX(), coracao.getY(), this);
				
			}
			graficos.setFont(carregarFonte2(fontFile));
			graficos.setColor(color);
			graficos.drawString("pontuação:"+calculaPontuacao(), 900, 50); 
			
			if(calculaPontuacao()<1000) {
				if(horda1!=null) {
					horda1.paint(graficos);
					
				}
					
				
			}
			if(calculaPontuacao()>=1000 && calculaPontuacao()<2000) {
			if(horda2!=null) {
				horda2.paint(graficos);
				
			}
					
				
			}
			if(calculaPontuacao()>=2000) {	
				if (horda3!=null) {
					horda3.paint(graficos);
					
				}
					
				
			}
			
		}
		g.dispose();
			
		}

	public void deslizamentoSingle(ActionEvent e) {

		if (this.single) {

			if (this.aumentando) {
				posicaoYSingle = posicaoYSingle - 2; // Aumenta a posição Y do texto
				if (posicaoYSingle <= 394) {
					this.aumentando = false; // Se atingir 406, começa a diminuir
				}
			} else {
				posicaoYSingle = posicaoYSingle + 2; // Diminui a posição Y do texto
				if (posicaoYSingle >= 406) {
					this.aumentando = true; // Se atingir 394, começa a aumentar
				}
			}

		}

	}

	public void verificarDerrota() {

		if (vidaPlayer <= 0 && vidaPlayer2 <= 0) {
			if (telaQueda == null) {
				telaQueda = new TelaQueda();
			}
			gameOver = true;
			emJogo = false;
			telaQueda.actionPerformed(null);

		}
	}

	

	public void tirarMenu(ActionEvent e) {
		if (emJogo) {
			if (posicaoYSingle < 1000) {
				posicaoYSingle += 10;
			}
			if (posicaoYMulti < 1000) {
				posicaoYMulti += 10;
			}
			if (posicaoYTitulo < 1000) {
				posicaoYTitulo += 10;
			}
		}
	}

	public void deslizamentoMulti(ActionEvent e) {

		if (multi) {

			if (this.aumentando2) {
				posicaoYMulti = posicaoYMulti - 2; // Aumenta a posição Y do texto
				if (posicaoYMulti <= 474) {
					this.aumentando2 = false; // Se atingir 406, começa a diminuir
				}
			} else {
				posicaoYMulti = posicaoYMulti + 2; // Diminui a posição Y do texto
				if (posicaoYMulti >= 486) {
					this.aumentando2 = true; // Se atingir 394, começa a aumentar
				}
			}
		}

	}

	private class TecladoAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			TelaInicio.this.keyPressed(e);
			player.keyPressed(e);
			if (player2 != null) {

				player2.keyPressed(e);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			player.keyReleased(e);
			if (player2 != null) {

				player2.keyReleased(e);
			}
		}
	}

	public void keyPressed(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();

		if (codigo == KeyEvent.VK_UP) {

			this.single = true;
			this.multi = false;
			colorMulti = new Color(218, 165, 32);
			colorSingle = Color.yellow;
			this.ySeta = 360;
			System.out.print("apertou para cima");
		}

		if (codigo == KeyEvent.VK_DOWN) {

			this.multi = true;
			this.single = false;
			colorSingle = new Color(218, 165, 32);
			colorMulti = Color.yellow;
			this.ySeta = 440;
			System.out.print("apertou para baixo");

		}
		if (codigo == KeyEvent.VK_ENTER) {

			if (!emJogo) {
				player.setCentralizado(0);
				this.jogo = true;
				System.out.print("centralizado setado");
				this.emJogo = true;

				if (this.ySeta == 440) {
					if (player2 == null) {
						player2 = new Player2();
						player2.setCentralizado(0);
						vidaPlayer2 = 6;

					}

				}

			}

		}

		if (codigo == KeyEvent.VK_T && gameOver) {
			// Resetar todas as variáveis para os valores iniciais da tela inicial
			emJogo = false;
			jogo = false;
			player = null;
			player = new Player();
			player.dadosImagem();// Reinicializa o jogador principal
			vidaPlayer = 6; // Reinicializa a vida do jogador principal
			abateInimigoRosa = 0;
			abateInimigoVerde = 0;
			abateInimigoLaranja = 0;
			abateInimigoAzul = 0;
			pontuacaoTotal = 0;
			horda1 = null;
			horda2 = null;
			horda3 = null;
			// Se houver um segundo jogador, reinicialize também
			if (player2 != null) {
				player2 = null;
				vidaPlayer2 = 0;
			}
			single = false;
			multi = false;
			colorSingle = new Color(218, 165, 32);
			colorMulti = new Color(218, 165, 32);
			ySeta = 360;
			posicaoYSingle = 400;
			posicaoXSingle = 200;
			posicaoYMulti = 480;
			posicaoXMulti = 200;
			posicaoYTitulo = 200;
			repaint(); // Redesenha a tela inicial com os novos valores
		}

	}

}
