package br.com.trabalho02.bean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import org.apache.commons.io.FileUtils;

@ManagedBean
public class UploaderAction {

	private static final long serialVersionUID = 1L;
	
	private static final String SUCCESS = "Success";
	private static final String ERROR = "Error";
	
	/** Upload **/
	private File fileUpload;
	private String fileUploadContentType;
	private String fileUploadFileName;
	
	/** Download **/
	public String filePath;
	public InputStream inputStream;
	
	//private HttpServletRequest servletRequest;
	
	private List<File> arquivos;
	
	private List<File> diretorios;
	private String diretorioAtual = "Home";
	
	private String novoDiretorio = "";
	private String diretorioDelete = "";
	private String fileDelete = "";
	
	private static List<String> caminho;
	
	private String tamanhoUsado;
	
	
	public String upload() throws Exception  {
		
		
		/*
		String PATH = servletRequest.getSession().getServletContext().getRealPath("\\") + "raiz" + getCaminhoPath();

        System.out.println("Server path:" + PATH);
        File fileToCreate = new File(PATH, this.fileUploadFileName);
        
        FileUtils.copyFile(this.fileUpload, fileToCreate);*/
        
        
        listar();
        
        return UploaderAction.SUCCESS;
    }
	
	public String download() throws Exception {
		
		/*String PATH = servletRequest.getSession().getServletContext().getRealPath("\\") + "raiz" + getCaminhoPath();
		
		if(filePath != null) {
			File f = new File(PATH + filePath);
			inputStream= new FileInputStream(f);
			return SUCCESS;
		}*/
		
		
		return ERROR;
	}
	
	
	public String getContentDisposition() {

		/*String PATH = servletRequest.getSession().getServletContext().getRealPath("\\") + "raiz" + getCaminhoPath();
		File f = new File(PATH + filePath);*/
		
		
		return"attachment;filename="/* + f.getName()*/;
	}
	
	public String criarDiretorio() throws Exception  {
		
		/*String PATH = servletRequest.getSession().getServletContext().getRealPath("\\") + "raiz"  + getCaminhoPath();
        System.out.println("New Directory:" + PATH + novoDiretorio);
        new File(PATH +  novoDiretorio).mkdirs();
        novoDiretorio  = "";
        listar();*/
        
        return UploaderAction.SUCCESS;
    }
	
	public String deleteDiretorio() throws Exception  {
		/*String PATH = servletRequest.getSession().getServletContext().getRealPath("\\") + "raiz" + getCaminhoPath();
		System.out.println("Server deleting directory:\n" + PATH + diretorioDelete);
        
		FileUtils.deleteDirectory(new File(PATH + "\\" + diretorioDelete));
        listar();*/
        return UploaderAction.SUCCESS;
    }
	
	public String deleteFile() throws Exception  {
		/*String PATH = servletRequest.getSession().getServletContext().getRealPath("\\") + "raiz" + getCaminhoPath();
		System.out.println("Server deleting file:\n" + PATH + fileDelete);
		new File((PATH + "\\" + fileDelete)).delete();
        listar();*/
        return UploaderAction.SUCCESS;
    }
	
	/**
	 *  Método usado para quando o usuário entrar em um diretório especificado
	 *  para visualizar seus arquivos.
	 *  @return
	 *  @throws Exception
	 */
	public String entrar() throws Exception  {
		if(caminho == null) {
			caminho = new ArrayList<String>();
		}
		caminho.add(diretorioAtual);
		
		listar();
		
        return UploaderAction.SUCCESS;
    }
	
	/**
	 * Usado quando o usuário clica no nome da pasta no breadcrumb.
	 * @return
	 * @throws Exception
	 */
	public String voltar() throws Exception  {
		int i = caminho.indexOf(diretorioAtual);
		
		for(i = i+1; i < caminho.size(); i++) {
			caminho.remove(i);
		}
		
		listar();
		
        return UploaderAction.SUCCESS;
    }
	
	/**
	 * Usado quando o usuário clica no botão de voltar
	 * @return
	 * @throws Exception 
	 */
	public String voltarBotao() throws Exception {
		int size = caminho.size();
		if(caminho.size() > 0) {
			caminho.remove(size-1);
		}
		
		if(caminho.size() > 0) {
			diretorioAtual = caminho.get(caminho.size()-1);
		}
		
		listar();
		return UploaderAction.SUCCESS;
	}
	
	public String resetaCaminho() throws Exception {
		 caminho = new ArrayList<String>();
		 listar();
		 return UploaderAction.SUCCESS;
	}
	
	public String index() throws Exception {
		diretorioAtual = "Home";
		caminho = new ArrayList<String>();
		listar();
		return UploaderAction.SUCCESS;
	}
	
	public String listar() throws Exception  {
		
		/*String ROOT_PATH = servletRequest.getSession().getServletContext().getRealPath("\\") + "raiz" + getCaminhoPath();
		System.out.println(ROOT_PATH);
		
		String files = "";
		File folder = new File(ROOT_PATH);
		File[] listOfFiles = folder.listFiles();
		
		arquivos = new ArrayList<File>();
		diretorios = new ArrayList<File>();
		
		if(listOfFiles != null) {
			for (int i = 0; i < listOfFiles.length; i++) {

				
				if (listOfFiles[i].isFile()) {
					
					files += listOfFiles[i].getName();
					arquivos.add(listOfFiles[i]);
					
				} else if (listOfFiles[i].isDirectory()) {
					
					files = listOfFiles[i].getName();
					diretorios.add(listOfFiles[i]);
		        }
			}
		}
		
		System.out.println(arquivos);*/
		
        return UploaderAction.SUCCESS;
    }
	
	public String getFormatedDate(File file) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
		return sdf.format(file.lastModified());
	}

	public String getCaminhoPath() {
		String caminhoRelativo = "";
		if(caminho != null) {
			caminhoRelativo = "\\";
			for (String cam : caminho) {
				caminhoRelativo += cam + "\\";
			}
		} else {
			caminho = new ArrayList<String>();
		}
		return caminhoRelativo;
	}
	
	public File getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String getFileUploadContentType() {
		return fileUploadContentType;
	}

	public void setFileUploadContentType(String fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}

	public String getFileUploadFileName() {
		return fileUploadFileName;
	}

	public void setFileUploadFileName(String fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}

	/*public HttpServletRequest getServletRequest() {
		return servletRequest;
	}

	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}*/


	public List<File> getArquivos() {
		return arquivos;
	}


	public void setArquivos(List<File> arquivos) {
		this.arquivos = arquivos;
	}


	public List<File> getDiretorios() {
		return diretorios;
	}


	public void setDiretorios(List<File> diretorios) {
		this.diretorios = diretorios;
	}


	public List<String> getCaminho() {
		return caminho;
	}


	public void setCaminho(List<String> caminho) {
		this.caminho = caminho;
	}


	public String getTamanhoUsado() {
		/*String ROOT_PATH = servletRequest.getSession().getServletContext().getRealPath("/") + "raiz";
		tamanhoUsado = FileUtils.sizeOfDirectory(new File(ROOT_PATH))/1024/1024 + "";*/
		return "" /*tamanhoUsado*/;
	}


	public void setTamanhoUsado(String tamanhoUsado) {
		this.tamanhoUsado = tamanhoUsado;
	}

	public String getDiretorioAtual() {
		return diretorioAtual;
	}

	public void setDiretorioAtual(String diretorioAtual) {
		this.diretorioAtual = diretorioAtual;
	}

	public String getNovoDiretorio() {
		return novoDiretorio;
	}

	public void setNovoDiretorio(String novoDiretorio) {
		this.novoDiretorio = novoDiretorio;
	}

	public String getDiretorioDelete() {
		return diretorioDelete;
	}

	public void setDiretorioDelete(String diretorioDelete) {
		this.diretorioDelete = diretorioDelete;
	}

	public String getFileDelete() {
		return fileDelete;
	}

	public void setFileDelete(String fileDelete) {
		this.fileDelete = fileDelete;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public InputStream getInputStream() throws FileNotFoundException {
		
		/*String PATH = servletRequest.getSession().getServletContext().getRealPath("\\") + "raiz" + getCaminhoPath();
		
		if(filePath != null) {
			File f = new File(PATH + filePath);
			inputStream= new FileInputStream(f);
			
		}*/
		
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	
	
}
