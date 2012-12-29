public static void memoryMappedWithArrayReading() {
// http://nadeausoftware.com/articles/2008/02/java_tip_how_read_files_quickly
    int SIZE = 8192;
		FileInputStream f = null;
		try {
			f = new FileInputStream( "test.txt" );
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		FileChannel ch = f.getChannel( );
		MappedByteBuffer mb = null;
		try {
			mb = ch.map( FileChannel.MapMode.READ_ONLY,
			    0L, ch.size( ) );
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] barray = new byte[SIZE];
		long checkSum = 0L;
		int nGet;
		while( mb.hasRemaining( ) )
		{
			StringBuilder sb = new StringBuilder();
		    nGet = Math.min( mb.remaining( ), SIZE );
		    mb.get( barray, 0, nGet );
		    for ( int i=0; i<nGet; i++ )
		        sb.append((char) barray[i]);
		    System.out.println(sb.toString());
		}

}
